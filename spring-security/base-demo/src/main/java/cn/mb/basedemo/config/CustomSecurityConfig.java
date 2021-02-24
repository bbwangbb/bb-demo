package cn.mb.basedemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * <p>
 *  自定义UserDetailService配置类
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/22
 */
//@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    //  注入数据源
    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //  自动创建表
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    //  配置密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  登出
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/hello");
        //  自定义403页面设置
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        http.formLogin()//  自定义登录页面
                .loginPage("/login.html")// 登录页面设置
                .loginProcessingUrl("/user/login")//    登录访问路径(即登录接口的url)
                .defaultSuccessUrl("/success.html").permitAll()// 登录成功后跳转路径
                .and().authorizeRequests()
                .antMatchers("/", "/hello", "/user/login").permitAll()//    放行路径
                //  基于权限的访问控制
                .antMatchers("/index").hasAuthority("index")//  有index权限才可以访问该资源(指定多个表示全部都有即可访问，用逗号隔开)
//                .antMatchers("/index").hasAnyAuthority("index1,index2")//  有index1/index2权限即可以访问该资源(指定多个表示有任意一个即可访问，用逗号隔开)
                //  基于角色的访问控制
//                .antMatchers("/index").hasRole("role")
//                .antMatchers("/index").hasAnyRole("role")// 类似hasAnyAuthority
                .anyRequest().authenticated()// 其他需要认证
                //  记住我的配置
                .and().rememberMe().tokenRepository(persistentTokenRepository())//  注入persistentTokenRepository
                .tokenValiditySeconds(60)// 有效时间(单位:s)
                .userDetailsService(userDetailsService)//   指定UserDetailsService
                .and().csrf().disable();//  禁用csrf
    }

}
