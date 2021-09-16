package cn.mb.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 配置类的属性
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/8/23
 */
@Data
@ConfigurationProperties(prefix = "bb")//   前缀
public class BBProperties {
    private String name;
    private Integer age;
}
