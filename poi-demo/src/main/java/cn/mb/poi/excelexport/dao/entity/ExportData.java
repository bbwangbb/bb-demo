package cn.mb.poi.excelexport.dao.entity;

import cn.mb.poi.excelexport.annotation.ExportField;

/**
 * <p>
 *  导出数据
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/26
 */
public class ExportData {

    @ExportField(desc = "姓名")
    private String  name;
    //  年龄
    private Integer age;
    @ExportField(desc = "性别")
    private String  sex;

    public ExportData() {
    }

    public ExportData(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
