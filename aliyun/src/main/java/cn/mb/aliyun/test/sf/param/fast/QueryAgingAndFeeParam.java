package cn.mb.aliyun.test.sf.param.fast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 运费时效查询参数
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryAgingAndFeeParam {

    /**
     * 寄件省
     */
    private String sendProvince;

    /**
     * 寄件城市
     */
    private String sendCity;

    /**
     * 寄件区
     */
    private String sendCounty;

    /**
     * 到件省
     */
    private String deliveryProvince;

    /**
     * 到件城市
     */
    private String deliveryCity;

    /**
     * 到件区
     */
    private String deliveryCounty;

    /**
     * 重量，单位千克
     */
    private String weight;

    /**
     * 体积，单位立方厘米
     */
    private String volume;

    /**
     * 总件数
     */
    private String totalNum;

}
