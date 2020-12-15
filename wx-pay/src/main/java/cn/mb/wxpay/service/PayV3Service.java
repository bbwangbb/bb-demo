package cn.mb.wxpay.service;


import cn.mb.wxpay.bean.PayV3Notify;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/2
 */
public interface PayV3Service {
    /**
     * <p>
     *  支付回调
     * </p>
     * @param payV3Notify   微信发送的数据
     * @return java.lang.String
     * @author guohaibin
     * @date 2020-12-02 14:14:29
     */
    String payNotify(PayV3Notify payV3Notify);
}
