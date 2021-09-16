package cn.mb.excel;

import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/7/30
 */
@Data
public class 车辆档案 {
    private String name;
    private String vin;
    private LocalDate saleDate;
    private Integer sn;
}
