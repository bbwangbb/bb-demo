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
        return new WxPayServiceImpl();
    }
}
