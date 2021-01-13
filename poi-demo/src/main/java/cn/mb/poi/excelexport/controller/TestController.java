package cn.mb.poi.excelexport.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.mb.poi.excelexport.dao.entity.ExportData;
import cn.mb.poi.excelexport.util.ExportUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  测试接口
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/26
 */
@RestController
public class TestController {

    private final ExportUtil exportUtil;

    public TestController(ExportUtil exportUtil) {
        this.exportUtil = exportUtil;
    }

    @GetMapping("/test")
    public void test() throws Exception {
        List<ExportData> rows = CollUtil.newArrayList();
        rows.add(new ExportData("bb", 22, "男"));
        rows.add(new ExportData("cc", 23, "男"));
        rows.add(new ExportData("dd", 24, "女"));
        rows.add(new ExportData("ee", 25, "女"));
        //  xlsx
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //  自定义操作
        writer.getSheet().setDefaultColumnWidth(22);
        exportUtil.export(ExportData.class, rows, "测试", "bb家族", writer);
    }

}
