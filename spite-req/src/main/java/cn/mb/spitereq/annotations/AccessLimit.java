package cn.mb.spitereq.annotations;

import java.lang.annotation.*;

/**
 * <p>
 *  访问限制注解
 *      单位时间最多访问多少次
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/23
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AccessLimit {

    /**
     * 单位时间(s)
     */
    int second();

    /**
     * 最大访问次数
     */
    int maxCount();
}
