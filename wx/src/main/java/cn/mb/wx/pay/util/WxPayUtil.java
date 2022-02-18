package cn.mb.wx.pay.util;

import cn.hutool.core.util.StrUtil;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>
 * 微信支付工具类
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/1
 */
@Component
@AllArgsConstructor
@ConditionalOnProperty(name = "wx.pay.enable")
public class WxPayUtil {

    private final WxPayService wxPayService;

    /**
     * <p>
     * JSAPI支付
     * </p>
     * @param body              商品简单描述(128长)
     * @param totalFee          订单总金额(分)
     * @param spbillCreateIp    终端IP
     * @param outTradeNo        订单号(需要生成)
     * @param openId            用户openId
     * @param productId         native支付必须指定
     * @return com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult    响应结果(包含paySign)，直接返回给前端使用，但需要替换packageValue的属性名
     * @author guohaibin
     * @date 2020-12-01 13:28:14
     */
    public Object createOrder(String body, BigDecimal totalFee, String spbillCreateIp,
                              String outTradeNo, String openId, String productId) throws Exception {
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
     * 关闭订单
     * </p>
     * @param outTradeNo    订单号
     * @return com.github.binarywang.wxpay.bean.result.WxPayOrderCloseResult
     * @author guohaibin
     * @date 2020-12-09 14:58:19
     */
    @Async
    public WxPayOrderCloseResult closeOrder(String outTradeNo) throws Exception {
        return wxPayService.closeOrder(outTradeNo);
    }

}
