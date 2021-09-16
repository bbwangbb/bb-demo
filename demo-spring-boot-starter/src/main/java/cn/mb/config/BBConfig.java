package cn.mb.config;

import cn.mb.properties.BBProperties;
import cn.mb.service.DemoService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 配置DemoService的配置类
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/8/23
 */
@Data
@Configuration
@EnableConfigurationProperties(BBProperties.class)//    有该类才自动装配
@ConditionalOnProperty(
        prefix = "bb",//    属性名前缀
        name = "isopen",//  变量名
        havingValue = "true"//  属性期望值
)// 条件注入(bb.isopen = true表示注入该bean)
public class BBConfig {

    @Autowired
    private BBProperties bbProperties;

    @Bean
    public DemoService demoService(){
        return new DemoService(bbProperties.getName(), bbProperties.getAge());
    }

}
