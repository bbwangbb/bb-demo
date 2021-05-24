package cn.mb.wxpay.service.impl;

import cn.mb.wxpay.config.WxConfig;
import cn.mb.wxpay.service.PayV2Service;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  支付服务
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/1
 */
@Slf4j
@Service
public class PayV2ServiceImpl implements PayV2Service {

    private final WxConfig wxConfig;

    /** 支付回调线程池 */
    private static final ThreadPoolExecutor payNotifyThreadPool = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            1L,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(300));

    public PayV2ServiceImpl(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    /**
     * 由于是代小程序开发，商户号未知，因而需要通过appId获取对应小程序商户密钥
     *  获取appId
     *  获取appId对应商户密钥
     *  校验签名
     *  获取订单
     *  判断订单支付状态
     *      已支付，直接返回
     *      未支付，校验金额后，修改订单状态
     */
    @Override
    public String payNotify(String xmlData) {
        try {
            WxPayOrderNotifyResult result = WxPayOrderNotifyResult.fromXML(xmlData);
            //  获取appId
            String appId = result.getAppid();
            //  获取appId对应商户密钥
            String mchKey = wxConfig.getMchKey();
            WxPayService wxPayService = new WxPayServiceImpl();
            WxPayConfig wxPayConfig = new WxPayConfig();
            wxPayConfig.setMchKey(mchKey);
            wxPayService.setConfig(wxPayConfig);
            //  校验签名，若为恶意请求会报错，除非您签名都能搞定
            result.checkResult(wxPayService, result.getSignType(), false);
            String outTradeNo = result.getOutTradeNo();
            //  异步执行逻辑，微信支付回调5S内不响应则算作超时
            payNotifyThreadPool.submit(() -> {
                //  订单号
                while (true) {
                    //  获取订单

                    //  判断订单状态

                    //  检验金额是否一致

                    //  并发修改
                    break;
                }
            });
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信支付回调异常,异常原因:{},参数为:{}", e.getMessage(), xmlData);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

    /**
     * 由于是代小程序开发，商户号未知，因而需要通过appId获取对应小程序商户密钥
     *  获取appId
     *  获取appId对应商户密钥
     *  获取退单
     *  判断退单支付状态
     *      已支付，直接返回
     *      未支付，校验金额后，修改订单状态
     */
    @Override
    public String refundNotify(String xmlData) {
        try {
            WxPayRefundNotifyResult result = BaseWxPayResult.fromXML(xmlData, WxPayRefundNotifyResult.class);

            //  获取appId
            String appId = result.getAppid();
            //  获取appId对应商户密钥
            String mchKey = wxConfig.getMchKey();
            WxPayService wxPayService = new WxPayServiceImpl();
            WxPayConfig wxPayConfig = new WxPayConfig();
            wxPayConfig.setMchKey(mchKey);
            wxPayService.setConfig(wxPayConfig);
            //  解密字段
            result.decryptReqInfo(mchKey);
            //  退单号
            String outRefundNo = result.getReqInfo().getOutRefundNo();
            while (true) {
                //  获取退单

                //  判断退单状态

                //  检验金额是否一致

                //  并发修改
                break;
            }
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信退款回调异常,异常原因:{},参数为:{}", e.getMessage(), xmlData);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
}
