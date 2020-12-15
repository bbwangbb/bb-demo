package cn.mb.wxpay.controller;

import cn.mb.wxpay.bean.PayV3Notify;
import cn.mb.wxpay.config.WxConfig;
import cn.mb.wxpay.service.PayV3Service;
import cn.mb.wxpay.util.WxPayV3Util;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 *  v3支付接口
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/1
 */
@RestController
@RequestMapping("/v3")
public class PayV3Controller {

    private final PayV3Service payV3Service;
    private final WxPayV3Util wxPayV3Util;
    private final WxConfig wxConfig;

    public PayV3Controller(PayV3Service payV3Service, WxPayV3Util wxPayV3Util, WxConfig wxConfig) {
        this.payV3Service = payV3Service;
        this.wxPayV3Util = wxPayV3Util;
        this.wxConfig = wxConfig;
    }

    /**
     * <p>
     *  下单
     * </p>
     * @param outTradeNo    订单号
     * @return com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult
     * @author guohaibin
     * @date 2020-12-02 13:59:33
     */
    @GetMapping("/prepay")
    public WxPayMpOrderResult prepay(String outTradeNo) throws Exception {
        //  TODO openId参数需要修改为appId下的支付用户的openId
        return wxPayV3Util.createOrderJSAPI(
                wxConfig.getAppId(), wxConfig.getMchId(), wxConfig.getApiV3Key(),
                "测试", BigDecimal.valueOf(0.01), outTradeNo,
                "oH-4D5Xyc3Csg3yKC3Bv_8fq4qjI", wxConfig.getPrivateKeyPath(), wxConfig.getPrivateCertPath(), false
        );
    }

    /**
     * <p>
     *  支付回调
     * </p>
     * @param payV3Notify   微信发送的数据
     * @return java.lang.String
     * @author guohaibin
     * @date 2020-12-02 14:14:29
     */
    @PostMapping("/payNotify")
    public String payNotify(@RequestBody PayV3Notify payV3Notify) throws Exception{
        //  需要考虑恶意调用，非法调用可以不考虑，因为有签名验证，但是要避免多次恶意调用
        return payV3Service.payNotify(payV3Notify);
    }

}