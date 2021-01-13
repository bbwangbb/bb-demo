package cn.mb.poi.excelexport.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  导入service
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/31
 */
public interface ImportService {
    /**
     * <p>
     *  导入excel(采用多线程导入)
     * </p>
     * @param file  文件
     * @return void
     * @author guohaibin
     * @date 2021-01-04 17:00:11
     */
    void importExcel(MultipartFile file) throws Exception;
}
