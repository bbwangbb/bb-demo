package cn.mb.excelexport.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *  导出字段
 *      还可以添加是否导出该字段属性以及其他自定义属性来扩充功能
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/26
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportField {

    /**
     * 描述
     */
    String desc();

}
