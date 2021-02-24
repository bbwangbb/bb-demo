package cn.mb.basedemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 *  基于内存的Security配置
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/22
 */
//@Configuration
public class MemorySecurityConfig extends WebSecurityConfigurerAdapter {
    //  配置密码加密器
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String psd = bCryptPasswordEncoder.encode("root");
        //  内存中配置用户
        auth.inMemoryAuthentication().withUser("root").password(psd).roles("admin");
    }
}