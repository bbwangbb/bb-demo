package cn.mb.basedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("cn.mb.basedemo.dao")
@EnableGlobalMethodSecurity(
        securedEnabled = true,//    启用@Secure注解
        prePostEnabled = true// 启用@PreAuthorize注解
)
public class BaseDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseDemoApplication.class);
    }
}
