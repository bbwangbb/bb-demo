package cn.mb.repeatrequestsolution.config;

import cn.mb.repeatrequestsolution.interceptors.RepeatRequestVerifyInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *  web配置
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/01/13
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RepeatRequestVerifyInterceptor repeatRequestVerifyInterceptor;

    public WebConfig(RepeatRequestVerifyInterceptor repeatRequestVerifyInterceptor) {
        this.repeatRequestVerifyInterceptor = repeatRequestVerifyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatRequestVerifyInterceptor);
    }
}
