package cn.mb.repeatrequestsolution.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *  请求参数校验
 *      避免重复请求
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqParamVerify {

    /**
     * 排除的参数数组
     */
    String[] excludeParams() default {};

}
