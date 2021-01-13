package cn.mb.poi.excelexport.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.mb.poi.excelexport.dao.entity.PoiTest;
import cn.mb.poi.excelexport.service.PoiTestService;
import cn.mb.poi.excelexport.util.ExportUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  大数据量导出接口
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/21
 */
@RestController
public class LargeDataExportController {

    private final ExportUtil exportUtil;
    private final PoiTestService poiTestService;

    public LargeDataExportController(ExportUtil exportUtil, PoiTestService poiTestService) {
        this.exportUtil = exportUtil;
        this.poiTestService = poiTestService;
    }

    @GetMapping("largeDataExport")
    public void largeDataExport() throws Exception {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //  自定义操作
        writer.getSheet().setDefaultColumnWidth(22);
        exportUtil.export(PoiTest.class, poiTestService.page(new Page<>(0, 300000)).getRecords(), "测试导出", "标题", writer);
    }

}
