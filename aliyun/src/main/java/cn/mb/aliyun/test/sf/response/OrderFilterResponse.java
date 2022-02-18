package cn.mb.aliyun.test.sf.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 订单筛选响应数据
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterResponse {
    /**
     * 客户订单号
     */
    private Boolean orderId;
    /**
     * 筛单结果：
     *  1：人工确认
     *  2：可收派
     *  3：不可以收派
     *  4 : 地址无法确认
     * 当filter_type=1时，不存在1值
     */
    private Integer filterResult;
    /**
     * 原寄地区域代码，如果可收派，此项不能为空
     */
    private String originCode;
    /**
     * 目的地区域代码，如果可收派，此项不能为空
     */
    private String destCode;
    /**
     * 如果filter_result=3时为必填，不可以收派的原因代码：
     *  1：收方超范围
     *  2：派方超范围
     *  3：其它原因
     */
    private String remark;
}
