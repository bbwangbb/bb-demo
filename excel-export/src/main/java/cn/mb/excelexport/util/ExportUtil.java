package cn.mb.excelexport.util;

import cn.hutool.poi.excel.ExcelWriter;
import cn.mb.excelexport.annotation.ExportField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 *  导出工具
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/26
 */
public class ExportUtil {

    /**
     * <p>
     *  导出excel
     *  格式
     *              标题(会合并字段数的单元格)
     *      列名1     列名2     ...
     *      行1数据1  行2数据2   ...
     * </p>
     * @param cls           导出类
     * @param rows          导出数据
     * @param fileName      文件名(不含后缀)
     * @param title         标题(第一行必须有标题)
     * @param writer        hutool Excel工具,ExcelUtil.getWriter()为导出xls,ExcelUtil.getWriter(true)为导出xlsx
     * @param request       请求
     * @param response      响应
     * @return void
     * @author guohaibin
     * @date 2020-11-26 14:17:00
     */
    public static  <T> void exportExcel(Class<T> cls, List<T> rows, String fileName, String title, ExcelWriter writer,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        //  首行合并单元格
        writer.merge(totalColumn, title);
        //  写入数据
        writer.write(rows, true);
        //  设置文件名
        String suffix = writer.isXlsx() ? ".xlsx" : ".xls";
        fileName = fileName + suffix;
        //
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
        response.setContentType("application/ms-excel;charset=utf-8");
        OutputStream out = response.getOutputStream();
        writer.flush(out);
        writer.close();
        out.close();
    }

}
