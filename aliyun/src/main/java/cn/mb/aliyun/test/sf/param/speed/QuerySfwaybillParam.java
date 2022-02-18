package cn.mb.aliyun.test.sf.param.speed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 清单运费查询参数
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuerySfwaybillParam {

    /**
     * 1:表示按订单查询 2:表示按运单查询
     */
    private String trackingType;
    /**
     * 订单号或运单号；
     */
    private String trackingNum;

}
