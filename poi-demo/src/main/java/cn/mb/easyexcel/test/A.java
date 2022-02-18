package cn.mb.easyexcel.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A {
    @ExcelProperty("订单号")
    @ColumnWidth(20)
    private String orderSn;
    @ExcelProperty("姓名")
    @ColumnWidth(15)
    private String name;
    @ExcelProperty("手机号")
    @ColumnWidth(15)
    private String phone;

    @ExcelProperty("商品")
    private String item;
}
