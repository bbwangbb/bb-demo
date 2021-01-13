package cn.mb.poi.excelexport.service.impl;

import cn.hutool.core.util.PageUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.mb.poi.excelexport.dao.entity.PoiTest;
import cn.mb.poi.excelexport.service.ImportService;
import cn.mb.poi.excelexport.task.ImportTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  导入service.impl
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/31
 */
@Slf4j
@Service
public class ImportServiceImpl implements ImportService {

    private static final ExecutorService importExcelThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public void importExcel(MultipartFile file) throws Exception {
        //  数据太多，在这一步就会oom - 如果可以的话，可以限制excel的行数
        ExcelReader excelReader = ExcelUtil.getReader(file.getInputStream());
        //  每次导入几条
        int size = 5000;
        //  前两行不是数据
        int noDateRows = 2;
        int page = PageUtil.totalPage(excelReader.getRowCount() - noDateRows, size);
        for (int i = 1; i <= page; i++) {
            //  read：左闭右闭，从0开始 - headerRowIndex是属性名行(没有则无法解析成实体) - 用户在第二行主动添加属性名
            List<PoiTest> poiTestList = excelReader.read(1, noDateRows * i, size * i + 1, PoiTest.class);
            importExcelThreadPool.execute(new ImportTask<>(poiTestList));
        }
    }

}