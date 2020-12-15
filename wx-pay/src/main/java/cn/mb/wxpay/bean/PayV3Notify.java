package cn.mb.wxpay.bean;

import lombok.Data;

/**
 * <p>
 *  v3支付通知JSON
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/2
 */
@Data
public class PayV3Notify {

    //  通知ID(通知的唯一ID)
    private String id;
    //  通知创建时间
    private String create_time;
    //  通知类型(通知的类型，支付成功通知的类型为TRANSACTION.SUCCESS)
    private String resource_type;
    //  通知数据类型
    private String event_type;
    //  回调摘要
    private String summary;
    //  通知数据
    private Resource resource;

    @Data
    public static class Resource {

        //  加密算法类型(对开启结果数据进行加密的加密算法，目前只支持AEAD_AES_256_GCM)
        private String algorithm;
        //  数据密文(Base64编码后的开启/停用结果数据密文)
        private String ciphertext;
        //  附加数据
        private String associated_data;
        //  随机串
        private String nonce;
        private String original_type;


    }
}
