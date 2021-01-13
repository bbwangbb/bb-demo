package cn.mb.wxpay.config;

import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *  微信配置
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/2
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WxConfig {
    private String appId;
    private String mchId;
    private String mchKey;
    private String certPath;
    private String apiV3Key;
    private String privateKeyPath;
    private String privateCertPath;

    @Bean
    public WxPayService getWxPayService() {
        /**
         * <p>
         *  注入WxPayService
         *      注意：
         *          如果是自己的小程序，可以共用一个
         *          如果是第三方，授权给其他小程序，那么就静态获取，每次new一个，要不然在设置config的时候会覆盖参数
         * </p>
         * @param
         * @return com.github.binarywang.wxpay.service.WxPayService
         * @author guohaibin
         * @date 2021-01-07 11:34:34
         */
        return new WxPayServiceImpl();
    }
}
