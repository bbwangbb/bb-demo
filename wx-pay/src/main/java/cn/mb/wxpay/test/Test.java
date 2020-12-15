package cn.mb.wxpay.test;

import cn.hutool.core.util.IdUtil;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.util.SignUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/8
 */
public class Test {
    public static void main(String[] args) {

        String prepayId = "wx081517195331512036797534dc0bf90000";

        WxPayMpOrderResult result = WxPayMpOrderResult.builder()
                .appId("wx17df887658185a51")
                .timeStamp(System.currentTimeMillis() + "")
                .nonceStr(IdUtil.fastSimpleUUID())
                .packageValue("prepay_id=" + prepayId)
                .signType("MD5")
                .build();

        result.setPaySign(SignUtils.createSign(result, "MD5", "2613fa5946944d4281855b5e63c8656d", null));
        System.out.println(result);

    }
}
