package cn.mb.wxpay.test;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayOrderQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;

import java.io.File;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/8
 */
public class Test {
    public static void main(String[] args) throws Exception {
        查订单();
    }

    public static void 查订单() throws Exception {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId("wx17df887658185a51");
        wxPayConfig.setMchId("1603847436");
        wxPayConfig.setMchKey("2613fa5946944d4281855b5e63c8656d");
        WxPayOrderQueryRequest wxPayOrderQueryRequest = new WxPayOrderQueryRequest();
        wxPayOrderQueryRequest.setOutTradeNo("DD20210415000012");
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        WxPayOrderQueryResult wxPayOrderQueryResult = wxPayService.queryOrder(wxPayOrderQueryRequest);
        System.out.println(wxPayOrderQueryResult);
    }

    public void native支付() throws Exception {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId("wx17df887658185a51");
        wxPayConfig.setMchId("1603847436");
        wxPayConfig.setMchKey("2613fa5946944d4281855b5e63c8656d");
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setOutTradeNo("DD20210409000007");
        wxPayUnifiedOrderRequest.setBody("测试支付");
        wxPayUnifiedOrderRequest.setTotalFee(1);
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setNotifyUrl("http://bbwangbb.ngrok.wendal.cn/v2/payNotify");
        wxPayUnifiedOrderRequest.setTradeType(WxPayConstants.TradeType.NATIVE);
        wxPayUnifiedOrderRequest.setProductId("1");
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        WxPayNativeOrderResult wxPayNativeOrderResult = wxPayService.createOrder(wxPayUnifiedOrderRequest);
        System.out.println(wxPayNativeOrderResult);
        byte[] scanPayQrcodeMode2 = wxPayService.createScanPayQrcodeMode2(wxPayNativeOrderResult.getCodeUrl(), new File("C:\\Users\\HUAWEI\\Desktop\\test2.png"), 500);
        String encode = Base64.encode(scanPayQrcodeMode2).replaceAll("\r\n", "");;
        System.out.println(encode.length());
        FileUtil.writeBytes(scanPayQrcodeMode2, "C:\\Users\\HUAWEI\\Desktop\\test1.png");
    }
}
