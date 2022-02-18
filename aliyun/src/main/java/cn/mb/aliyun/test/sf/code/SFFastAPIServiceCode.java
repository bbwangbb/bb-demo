package cn.mb.aliyun.test.sf.code;

/**
 * <p>
 * 顺丰速运API 接口服务代码
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/11/11
 */
public interface SFFastAPIServiceCode {

    /**
     * 下订单
     */
    String CREATE_ORDER = "EXP_RECE_CREATE_ORDER";
    /**
     * 清单运费查询
     */
    String QUERY_SFWAYBILL = "EXP_RECE_QUERY_SFWAYBILL";
    /**
     * 时效及价格查询
     */
    String QUERY_DELIVERTM = "EXP_RECE_QUERY_DELIVERTM";
    /**
     * 订单筛选
     */
    String FILTER_ORDER = "EXP_RECE_FILTER_ORDER_BSP";


}
