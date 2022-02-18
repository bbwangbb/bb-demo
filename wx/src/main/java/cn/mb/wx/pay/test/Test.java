package cn.mb.wx.pay.test;

import com.github.binarywang.wxpay.bean.entpay.EntPayBankRequest;
import com.github.binarywang.wxpay.bean.entpay.EntPayBankResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/11
 */
public class Test {

    private final static String openId = "oL1UV5NaW5tSqeIIxC5rI9JJ5hZE";
    private final static String appId = "wxed0e4d28d71ae432";
    private final static String mchId = "1603847436";
    private final static String mchKey = "2613fa5946944d4281855b5e63c8656d";
    private final static String notifyUrl = "https://www.baidu.com";
    private final static String body = "test body";
    private final static String outTradeNo = "SPM_PAY20220116000008";
    private final static String outRefundNo = "TK21111";

    public static void main(String[] args) throws Exception {

//        下单();
//        退款();
        付款到银行卡();
    }

    //  需要开通此功能
    private static void 付款到银行卡() throws Exception {
        WxPayConfig wxPayConfig = new WxPayConfig();
        //  设置appid、商户号、商户密钥、证书
        wxPayConfig.setAppId(appId);
        wxPayConfig.setMchId(mchId);
        wxPayConfig.setMchKey(mchKey);
        wxPayConfig.setKeyPath("E:\\cheG\\data\\mch-cert\\1603847436\\apiclient_cert.p12");
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        EntPayBankRequest entPayBankRequest = new EntPayBankRequest();
        entPayBankRequest.setPartnerTradeNo("FK1");
        entPayBankRequest.setEncBankNo("6222022201019209842");
        entPayBankRequest.setEncTrueName("郭海斌");
        entPayBankRequest.setBankCode("1002");
        entPayBankRequest.setAmount(1);
        entPayBankRequest.setDescription("转账测试");
        EntPayBankResult entPayBankResult = wxPayService.getEntPayService().payBank(entPayBankRequest);
        System.out.println(entPayBankResult);
    }

    private static void 退款() throws Exception {
        WxPayConfig wxPayConfig = new WxPayConfig();
        //  设置appid、商户号、商户密钥、证书
        wxPayConfig.setAppId(appId);
        wxPayConfig.setMchId(mchId);
        wxPayConfig.setMchKey(mchKey);
        wxPayConfig.setKeyPath("E:\\cheG\\data\\mch-cert\\1603847436\\apiclient_cert.p12");
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        //  配置退款参数：下单的订单号、退款单号、支付总额、退款额
        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        wxPayRefundRequest.setNotifyUrl("http://bbwangbb.ngrok.wendal.cn/order/orderRefundNotify");
        wxPayRefundRequest.setOutTradeNo(outTradeNo);
        wxPayRefundRequest.setOutRefundNo(outRefundNo);
        wxPayRefundRequest.setTotalFee(1);
        wxPayRefundRequest.setRefundFee(1);
        WxPayRefundResult refund = wxPayService.refund(wxPayRefundRequest);
        System.out.println(refund);

    }

    private static void 下单() throws Exception {
        String tradeType = WxPayConstants.TradeType.JSAPI;
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(appId);
        wxPayConfig.setMchId(mchId);
        wxPayConfig.setMchKey(mchKey);
        wxPayConfig.setSignType(WxPayConstants.SignType.MD5);
        wxPayConfig.setTradeType(tradeType);
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        wxPayConfig.setNotifyUrl(notifyUrl);
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody(body);
        wxPayUnifiedOrderRequest.setTotalFee(BaseWxPayRequest.yuanToFen("0.01"));
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setOutTradeNo(outTradeNo);
        wxPayUnifiedOrderRequest.setOpenid(openId);
        Object order = wxPayService.createOrder(wxPayUnifiedOrderRequest);
        System.out.println(order);
    }

}
