package cn.mb.basedemo.config;

import cn.mb.basedemo.component.MyAccessDeniedHandler;
import cn.mb.basedemo.component.MyAuthenticationEntryPoint;
import cn.mb.basedemo.component.MyTokenFilter;
import cn.mb.basedemo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/23
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //不需要保护的资源路径允许访问
        //允许跨域请求的OPTIONS请求
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                //  放行url
                .and().authorizeRequests().antMatchers("/", "/admin/login", "/hello").permitAll()
                // 其他请求需要身份认证
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                // 自定义权限拦截器JWT过滤器
                .and()
                //  在进入UsernamePasswordAuthenticationFilter前将token解析为UserDetails
                .addFilterBefore(myTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new MyAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler restfulAccessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

    @Bean
    public MyTokenFilter myTokenFilter() {
        return new MyTokenFilter();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

}

