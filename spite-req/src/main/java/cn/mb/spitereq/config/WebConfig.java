package cn.mb.spitereq.config;

import cn.mb.spitereq.interceptors.SpiteReqInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *  web配置
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SpiteReqInterceptor spiteReqInterceptor;

    public WebConfig(SpiteReqInterceptor spiteReqInterceptor) {
        this.spiteReqInterceptor = spiteReqInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(spiteReqInterceptor);
    }
}
