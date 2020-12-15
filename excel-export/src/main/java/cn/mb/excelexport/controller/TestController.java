package cn.mb.excelexport.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.mb.excelexport.entity.ExportData;
import cn.mb.excelexport.util.ExportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  测试接口
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/26
 */
@Controller
public class TestController {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public TestController(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @GetMapping("/test")
    public void test() throws Exception {
        List<ExportData> rows = CollUtil.newArrayList();
        rows.add(new ExportData("bb", 22, "男"));
        rows.add(new ExportData("cc", 23, "男"));
        rows.add(new ExportData("dd", 24, "女"));
        rows.add(new ExportData("ee", 25, "女"));
        //  xls
        ExcelWriter writer = ExcelUtil.getWriter();
        //  xlsx
//        ExcelWriter writer = ExcelUtil.getWriter(true);
        //  自定义操作
        writer.getSheet().setDefaultColumnWidth(22);
        ExportUtil.exportExcel(ExportData.class, rows, "测试", "bb家族", writer, request, response);
    }

}
