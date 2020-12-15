package cn.mb.wxpay.service;

/**
 * <p>
 *  支付服务
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/1
 */
public interface PayV2Service {

    /**
     * <p>
     *  支付回调
     * </p>
     * @param xmlData   微信发送的数据
     * @return java.lang.String
     * @author guohaibin
     * @date 2020-12-02 08:59:59
     */
    String payNotify(String xmlData);

    /**
     * <p>
     *  退款回调
     * </p>
     * @param xmlData   微信发送的数据
     * @return java.lang.String
     * @author guohaibin
     * @date 2020-12-02 08:59:59
     */
    String refundNotify(String xmlData);
}
