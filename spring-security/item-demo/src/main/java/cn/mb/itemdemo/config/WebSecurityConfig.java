package cn.mb.itemdemo.config;

import cn.hutool.core.util.ArrayUtil;
import cn.mb.itemdemo.component.CustomAccessDeniedHandler;
import cn.mb.itemdemo.component.CustomAuthFilter;
import cn.mb.itemdemo.component.CustomAuthenticationEntryPoint;
import cn.mb.itemdemo.component.CustomTokenFilter;
import cn.mb.itemdemo.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/24
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final CustomAuthFilter customAuthFilter;
    private final CustomTokenFilter customTokenFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public WebSecurityConfig(UserService userService, IgnoreUrlsConfig ignoreUrlsConfig, CustomAuthFilter customAuthFilter, CustomAccessDeniedHandler customAccessDeniedHandler, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, CustomTokenFilter customTokenFilter) {
        this.userService = userService;
        this.ignoreUrlsConfig = ignoreUrlsConfig;
        this.customAuthFilter = customAuthFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customTokenFilter = customTokenFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  允许跨域请求的OPTIONS请求
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll();
        //  安全路径白名单
        http.authorizeRequests().antMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class)).permitAll();
        //  其他请求需认证
        http.authorizeRequests().anyRequest().authenticated();
        //  关闭跨站请求防护及不使用session
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //  配置自定义处理器
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint);
        //  自定义token解析器
        http.addFilterBefore(customTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //  自定义权限过滤器
        http.addFilterBefore(customAuthFilter, FilterSecurityInterceptor.class);
    }
}
