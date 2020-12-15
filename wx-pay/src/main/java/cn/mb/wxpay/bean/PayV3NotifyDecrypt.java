package cn.mb.wxpay.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 *  v3支付通知JSON
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/2
 */
@Data
public class PayV3NotifyDecrypt {

    //  微信支付订单号
    private String transaction_id;
    //  +订单金额
    private Amount amount;
    //  商户号
    private String mchid;
    //  交易状态
    private String trade_state;
    //  付款银行
    private String bank_type;
    //  支付完成时间
    private String success_time;
    //  支付者
    private Payer payer;
    //  订单号
    private String out_trade_no;
    //  小程序appId
    private String appid;
    //  交易状态描述
    private String trade_state_desc;
    //  交易类型
    private String trade_type;
    //  附加数据
    private String attach;

    @Data
    public static class Amount {

        //  用户支付金额
        private BigDecimal payer_total;
        //  总金额
        private BigDecimal total;
        //  货币类型
        private String currency;
        //  用户支付币种
        private String payer_currency;

    }

    @Data
    public static class Payer {

        //  用户标识openId
        private String openid;
    }
}
