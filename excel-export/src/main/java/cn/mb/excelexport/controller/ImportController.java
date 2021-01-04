package cn.mb.excelexport.controller;

import cn.mb.excelexport.service.ImportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  导入controller
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/31
 */
@RestController
public class ImportController {

    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    /**
     * <p>
     *  导入excel(采用多线程导入)
     * </p>
     * @param file  文件
     * @return void
     * @author guohaibin
     * @date 2021-01-04 17:00:11
     */
    @PostMapping("/importExcel")
    public void importExcel(MultipartFile file) throws Exception {
        importService.importExcel(file);
    }

}
