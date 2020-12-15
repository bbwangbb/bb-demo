package cn.mb.wxpay.controller;

import cn.hutool.core.io.FileUtil;
import cn.mb.wxpay.config.WxConfig;
import cn.mb.wxpay.service.PayV2Service;
import cn.mb.wxpay.util.WxPayV2Util;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * <p>
 *  v2支付接口
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/1
 */
@RestController
@RequestMapping("/v2")
public class PayV2Controller {

    private final PayV2Service payV2Service;
    private final WxPayV2Util wxPayV2Util;
    private final WxConfig wxConfig;
    private final HttpServletRequest request;

    public PayV2Controller(PayV2Service payV2Service, WxPayV2Util wxPayV2Util, WxConfig wxConfig, HttpServletRequest request) {
        this.payV2Service = payV2Service;
        this.wxPayV2Util = wxPayV2Util;
        this.wxConfig = wxConfig;
        this.request = request;
    }

    /**
     * <p>
     *  下单
     * </p>
     * @param outTradeNo    订单号
     * @return com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult
     * @author guohaibin
     * @date 2020-12-02 08:59:11
     */
    @GetMapping("/prepay")
    public WxPayMpOrderResult prepay(String outTradeNo) throws Exception {
        //  TODO openId参数需要修改为appId下的支付用户的openId
        return wxPayV2Util.createOrder(
                wxConfig.getAppId(), wxConfig.getMchId(), wxConfig.getMchKey(),
                "测试", BigDecimal.valueOf(0.01), "127.0.0.1",
                outTradeNo, "oH-4D5Xyc3Csg3yKC3Bv_8fq4qjI", WxPayConstants.TradeType.JSAPI, null
        );
    }

    /**
     * <p>
     *  支付回调
     * </p>
     * @param xmlData   微信发送的数据
     * @return java.lang.String
     * @author guohaibin
     * @date 2020-12-02 08:59:59
     */
    @PostMapping("/payNotify")
    public String payNotify(@RequestBody String xmlData) {
        //  需要考虑恶意调用，非法调用可以不考虑，因为有签名验证，但是要避免多次恶意调用
        return payV2Service.payNotify(xmlData);
    }

    /**
     * <p>
     *  退款
     * </p>
     * @param outTradeNo    订单号
     * @return com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult
     * @author guohaibin
     * @date 2020-12-02 08:59:11
     */
    @GetMapping("/refund")
    public WxPayRefundResult refund(String outTradeNo) throws Exception {
        return wxPayV2Util.refund(
                wxConfig.getAppId(), wxConfig.getMchId(), wxConfig.getMchKey(),
                FileUtil.readBytes(wxConfig.getCertPath()), outTradeNo, outTradeNo,
                BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.01)
        );
    }

    /**
     * <p>
     *  退款回调
     * </p>
     * @param xmlData   微信发送的数据
     * @return java.lang.String
     * @author guohaibin
     * @date 2020-12-02 08:59:59
     */
    @PostMapping("/refundNotify")
    public String refundNotify(@RequestBody String xmlData) {
        //  需要考虑恶意调用，非法调用可以不考虑，因为有签名验证，但是要避免多次恶意调用
        return payV2Service.refundNotify(xmlData);
    }

}