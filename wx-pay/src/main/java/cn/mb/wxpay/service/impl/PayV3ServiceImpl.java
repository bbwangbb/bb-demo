package cn.mb.wxpay.service.impl;

import cn.hutool.json.JSONUtil;
import cn.mb.wxpay.bean.PayV3Notify;
import cn.mb.wxpay.bean.PayV3NotifyDecrypt;
import cn.mb.wxpay.config.WxConfig;
import cn.mb.wxpay.service.PayV3Service;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/2
 */
@Slf4j
@Service
public class PayV3ServiceImpl implements PayV3Service {

    private final WxConfig wxConfig;

    public PayV3ServiceImpl(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    /**
     * 代小程序开发无法使用v3，因为返回的数据无法找到对应的小程序，则无法关联找到api v3 key
     * 只能单个小程序实现
     * 亦或大家都绑定我们的商户，感觉也不太可能
     */
    @Override
    public String payNotify(PayV3Notify payV3Notify) {
        try {
            //  解密
            String decryptToString = AesUtils.decryptToString(payV3Notify.getResource().getAssociated_data(),
                    payV3Notify.getResource().getNonce(), payV3Notify.getResource().getCiphertext(),
                    wxConfig.getApiV3Key());
            PayV3NotifyDecrypt payV3NotifyDecrypt = JSONUtil.toBean(decryptToString, PayV3NotifyDecrypt.class);
            //  订单号
            String outTradeNo = payV3NotifyDecrypt.getOut_trade_no();
            //  判断订单状态

            //  修改订单状态
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信v3支付回调异常,异常原因:{},参数为:{}", e.getMessage(), payV3Notify);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
}
