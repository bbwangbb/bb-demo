package cn.mb.excelexport.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.mb.excelexport.annotation.ExportField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  导出工具
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/26
 */
@Component
public class ExportUtil {

    @Value("${export.dir}")
    private String exportDir;
    //  XLS文件每个sheet写多少数据(最大行数：65535)
    private final int XLS_PER_SHEET_ROWS = 50000;
    //  XLSX文件每个sheet写多少数据(最大行数：1048575)
    private final int XLSX_PER_SHEET_ROWS = 200000;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public ExportUtil(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * <p>
     *  导出excel
     *      格式
     *                  标题(会合并字段数的单元格)
     *          列名1     列名2     ...
     *          行1数据1  行2数据2   ...
     * </p>
     * @param cls           导出类
     * @param rows          导出数据
     * @param fileName      文件名(不含后缀)
     * @param title         标题(第一行必须有标题)
     * @param writer        ExcelUtil.getWriter(isXlsx)获取
     * @return void
     * @author guohaibin
     * @date 2020-12-21 14:59:58
     */
    public <T> void export(Class<T> cls, List<T> rows, String fileName, String title, ExcelWriter writer) throws Exception {
        dealTitle(cls, title, writer);
        int maxRows = writer.isXlsx() ? XLS_PER_SHEET_ROWS : XLSX_PER_SHEET_ROWS;
        if (rows.size() + 2 > maxRows) {//  大数据量导出采用压缩导出
            exportZip(rows, fileName, maxRows, writer);
        } else {
            exportExcelFile(rows, fileName, writer);
        }
    }

    /**
     * <p>
     *  导出zip，数据量超过系统中限制的数量时，采用多文件压缩方式导出
     *  若是多sheet，一个writer可能会导致oom
     * </p>
     * @param rows      数据
     * @param fileName  文件名
     * @param maxRows   最大行数
     * @param writer    ExcelWriter
     * @return void
     * @author guohaibin
     * @date 2020-12-21 15:37:17
     */
    public <T> void exportZip(List<T> rows, String fileName, int maxRows, ExcelWriter writer) throws Exception {
        //  今日所有文件目录
        String todayDir = exportDir + LocalDateTimeUtil.format(LocalDate.now(), "yyyyMMdd") + "/";
        //  此次临时存储excel文件的路径
        String tempDir = todayDir + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        //  创建目录
        FileUtil.mkdir(tempDir);
        //  分页
        int page = PageUtil.totalPage(rows.size(), maxRows);
        OutputStream outputStream;
        List<T> currentRows;
        //  分页生成文件
        for (int i = 0; i < page; i++) {
            outputStream = new FileOutputStream(tempDir + "/" + fileName + i + ((writer.isXlsx() ? ".xlsx" : ".xls")));
            currentRows = rows.subList(i * maxRows, i == page - 1 ? rows.size() : (i + 1) * maxRows);
            writer.write(currentRows, true);
            writer.flush(outputStream);
            //  重新从第二行开始设值(此时头行是合并单元格，不管)
            writer.setCurrentRow(1);
        }
        fileName += ".zip";
        //  压缩zip
        ZipUtil.zip(tempDir, todayDir + fileName);
        InputStream is = new FileInputStream(todayDir + fileName);
        setResponse(true, fileName);
        //  写回
        IoUtil.copy(is, response.getOutputStream());
        response.flushBuffer();
    }

    /**
     * <p>
     *  导出excel文件
     * </p>
     * @param rows      数据
     * @param fileName  文件名
     * @param writer    ExcelWriter
     * @return void
     * @author guohaibin
     * @date 2020-12-21 15:36:50
     */
    public  <T> void exportExcelFile(List<T> rows, String fileName, ExcelWriter writer) throws Exception {
        writer.write(rows, true);
        setResponse(false, fileName + (writer.isXlsx() ? ".xlsx" : ".xls"));
        OutputStream out = response.getOutputStream();
        writer.flush(out);
        writer.close();
        out.close();
    }

    /**
     * <p>
     *  处理标题及列表名
     * </p>
     * @param cls       导出类
     * @param title     标题
     * @param writer    ExcelWriter
     * @return void
     * @author guohaibin
     * @date 2020-12-21 15:35:19
     */
    private <T> void dealTitle(Class<T> cls, String title, ExcelWriter writer) {
        //  首行需合并多少单元格
        int totalColumn = 0;
        Field[] fields = cls.getDeclaredFields();
        //  配置列名
        for (Field field : fields) {
            ExportField annotation = field.getDeclaredAnnotation(ExportField.class);
            if (annotation == null) continue;
            writer.addHeaderAlias(field.getName(), annotation.desc());
            totalColumn++;
        }
        writer.merge(totalColumn, title);
    }

    /**
     * <p>
     *  设置响应头(zip和xls/xlsx区别是ContentType不一样)
     * </p>
     * @param isZip     是否是zip文件
     * @param fileName  文件名
     * @return void
     * @author guohaibin
     * @date 2020-12-21 15:35:55
     */
    private void setResponse(boolean isZip, String fileName) throws Exception {
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident") || null != agent && -1 != agent.indexOf("Edge")) {// ie浏览器及Edge浏览器
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
                // 火狐,Chrome等浏览器
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        if (isZip) {
            response.setContentType("application/zip;charset=utf-8");
        } else {
            response.setContentType("application/ms-excel;charset=utf-8");
        }
    }

}
