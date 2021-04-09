package cn.mb.wxpay.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.*;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>
 *  微信支付工具类
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/1
 */
@Component
public class WxPayV2Util {

    @Value("${wx.v2PayNotifyUrl}")
    private String v2PayNotifyUrl;
    @Value("${wx.v2RefundNotifyUrl}")
    private String v2RefundNotifyUrl;

    private final WxPayService wxPayService;

    public WxPayV2Util(WxPayService wxPayService) {
        this.wxPayService = wxPayService;
    }

    /**
     * <p>
     *  统一下单(各种下单都可调用)
     * </p>
     * @param appId             小程序appId
     * @param mchId             小程序绑定商户号
     * @param mchKey            商户密钥
     * @param body              商品简单描述(128长)
     * @param totalFee          订单总金额(分)
     * @param spbillCreateIp    终端IP
     * @param outTradeNo        订单号(需要生成)
     * @param openId            用户openId
     * @param tradeType         交易类型
     * @param productId         native支付必须指定
     * @return com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult    响应结果(包含paySign)，直接返回给前端使用，但需要替换packageValue的属性名
     * @author guohaibin
     * @date 2020-12-01 13:28:14
     */
    public WxPayMpOrderResult createOrder(String appId, String mchId, String mchKey,
                                          String body, BigDecimal totalFee, String spbillCreateIp,
                                          String outTradeNo, String openId, String tradeType, String productId) throws Exception {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(appId);
        wxPayConfig.setMchId(mchId);
        if (StrUtil.isNotEmpty(mchKey)) wxPayConfig.setMchKey(mchKey);
        wxPayConfig.setSignType(WxPayConstants.SignType.MD5);
        if (StrUtil.isNotEmpty(mchKey)) wxPayConfig.setTradeType(tradeType);
        if (ObjectUtil.notEqual(WxPayConstants.TradeType.NATIVE, tradeType)) wxPayConfig.setNotifyUrl(v2PayNotifyUrl);
        wxPayService.setConfig(wxPayConfig);
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody(body);
        wxPayUnifiedOrderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(totalFee.toString()));
        wxPayUnifiedOrderRequest.setSpbillCreateIp(spbillCreateIp);
        wxPayUnifiedOrderRequest.setOutTradeNo(outTradeNo);
        if (StrUtil.isNotEmpty(openId)) wxPayUnifiedOrderRequest.setOpenid(openId);
        if (StrUtil.isNotEmpty(productId)) wxPayUnifiedOrderRequest.setProductId(productId);
        return wxPayService.createOrder(wxPayUnifiedOrderRequest);
    }

    /**
     * <p>
     *  订单查询
     * </p>
     * @param appId         小程序appId
     * @param mchId         小程序绑定商户号
     * @param mchKey        商户密钥
     * @param outTradeNo    订单号
     * @return com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult   查询结果
     * @author guohaibin
     * @date 2020-12-02 09:49:28
     */
    public WxPayOrderQueryResult queryOrder(String appId, String mchId, String mchKey, String outTradeNo) throws Exception {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(appId);
        wxPayConfig.setMchId(mchId);
        wxPayConfig.setMchKey(mchKey);
        WxPayOrderQueryRequest wxPayOrderQueryRequest = new WxPayOrderQueryRequest();
        wxPayOrderQueryRequest.setOutTradeNo(outTradeNo);
        wxPayService.setConfig(wxPayConfig);
        return wxPayService.queryOrder(wxPayOrderQueryRequest);
    }

    /**
     * <p>
     *  退款
     * </p>
     * @param appId         小程序appId
     * @param mchId         小程序绑定商户号
     * @param mchKey        商户密钥
     * @param keyContent    证书二进制流
     *                      若为本地文件：FileUtil.readBytes("文件名")
     *                      若为url文件：IoUtil.readBytes(URLUtil.getStream(URLUtil.url("证书地址"))) - 注意放在oss上会不安全
     * @param outTradeNo    订单号(针对哪个订单)
     * @param outRefundNo   退款单号(需要生成)
     * @param totalFee      订单总金额(分)
     * @param refundFee     退款金额(分)
     * @return com.github.binarywang.wxpay.bean.result.WxPayRefundResult    退款结果
     * @author guohaibin
     * @date 2020-12-01 13:59:00
     */
    public WxPayRefundResult refund(String appId, String mchId, String mchKey,
                              byte[] keyContent, String outTradeNo, String outRefundNo,
                              BigDecimal totalFee, BigDecimal refundFee) throws Exception {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(appId);
        wxPayConfig.setMchId(mchId);
        wxPayConfig.setMchKey(mchKey);
        wxPayConfig.setKeyContent(keyContent);
        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        wxPayRefundRequest.setOutTradeNo(outTradeNo);
        wxPayRefundRequest.setOutRefundNo(outRefundNo);
        wxPayRefundRequest.setTotalFee(BaseWxPayRequest.yuanToFen(totalFee.toString()));
        wxPayRefundRequest.setRefundFee(BaseWxPayRequest.yuanToFen(refundFee.toString()));
        wxPayRefundRequest.setNotifyUrl(v2RefundNotifyUrl);
        wxPayService.setConfig(wxPayConfig);
        WxPayRefundResult refund = wxPayService.refund(wxPayRefundRequest);
        return refund;
    }

    /**
     * <p>
     *  退款查询
     * </p>
     * @param appId         小程序appId
     * @param mchId         小程序绑定商户号
     * @param mchKey        商户密钥
     * @param outRefundNo   退款单号
     * @return com.github.binarywang.wxpay.bean.result.WxPayRefundQueryResult   查询结果
     * @author guohaibin
     * @date 2020-12-02 09:49:28
     */
    public WxPayRefundQueryResult refundQuery(String appId, String mchId, String mchKey, String outRefundNo) throws Exception {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(appId);
        wxPayConfig.setMchId(mchId);
        wxPayConfig.setMchKey(mchKey);
        WxPayRefundQueryRequest wxPayRefundQueryRequest = new WxPayRefundQueryRequest();
        wxPayRefundQueryRequest.setOutRefundNo(outRefundNo);
        wxPayService.setConfig(wxPayConfig);
        return wxPayService.refundQuery(wxPayRefundQueryRequest);
    }
}
