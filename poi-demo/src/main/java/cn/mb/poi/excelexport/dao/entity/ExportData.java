package cn.mb.poi.excelexport.dao.entity;

import cn.mb.poi.excelexport.annotation.ExportField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 *  导出数据
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportData {

    @ExportField(desc = "姓名")
    private String  name;
    //  年龄
    private Integer age;
    @ExportField(desc = "性别")
    private String  sex;
    @ExportField(desc = "列表")
    private List<String> stringList;

}
