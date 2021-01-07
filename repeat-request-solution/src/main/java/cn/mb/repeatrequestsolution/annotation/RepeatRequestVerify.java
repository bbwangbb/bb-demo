package cn.mb.repeatrequestsolution.annotation;

import cn.mb.repeatrequestsolution.enums.RepeatRequestVerifyMethod;

import java.lang.annotation.*;

/**
 * <p>
 *  重复请求校验注解
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/1/7
 */
@Documented
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatRequestVerify {

    /**
     * 重复请求校验方式
     */
    RepeatRequestVerifyMethod method();

    /**
     * 排除的参数数组
     */
    String[] excludeParams() default {};
}
