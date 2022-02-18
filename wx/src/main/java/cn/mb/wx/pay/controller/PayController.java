package cn.mb.wx.pay.controller;

import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/pay")
public class PayController {

    private final WxPayService wxPayService;

    @GetMapping("/refund")
    public void refund() throws Exception {
        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        wxPayRefundRequest.setOutTradeNo("TEST1");
        wxPayRefundRequest.setOutRefundNo("20220112REFUND1");
        wxPayRefundRequest.setTotalFee(1);
        wxPayRefundRequest.setRefundFee(1);
        wxPayRefundRequest.setNotifyUrl("http://bbwangbb.ngrok.wendal.cn/order/orderRefundNotify");
        WxPayRefundResult refund = wxPayService.refund(wxPayRefundRequest);
        System.out.println(refund);
    }

    @GetMapping("/createOrder")
    public void createOrder() throws Exception {
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setOpenid("oL1UV5NaW5tSqeIIxC5rI9JJ5hZE");
        wxPayUnifiedOrderRequest.setBody("test");
        wxPayUnifiedOrderRequest.setOutTradeNo("TEST2");
        wxPayUnifiedOrderRequest.setTotalFee(1);
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setNotifyUrl("https://www.baidu.com");
        wxPayUnifiedOrderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
        Object order = wxPayService.createOrder(wxPayUnifiedOrderRequest);
        System.out.println(order);
    }

    @GetMapping("/closeOrder")
    public void closeOrder() throws Exception {
        WxPayOrderCloseResult wxPayOrderCloseResult = wxPayService.closeOrder("TEST2");
        System.out.println(wxPayOrderCloseResult);
    }

}
