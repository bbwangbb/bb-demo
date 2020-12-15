package cn.mb.customannotation.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *  贴上该注解的类/方法会做日志操作
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/23
 */
@Documented//   可被文档导出
@Retention(value = RetentionPolicy.RUNTIME)//   保留状态，RUNTIME：可以通过反射读取
@Target(value = {ElementType.METHOD, ElementType.TYPE})//   作用域：方法、类
public @interface Log {
}
