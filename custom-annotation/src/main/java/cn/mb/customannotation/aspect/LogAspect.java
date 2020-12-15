package cn.mb.customannotation.aspect;

import cn.mb.customannotation.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 *  日志切面
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/23
 */
@Aspect
@Component
public class LogAspect {

    private static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut(value = " execution(public * cn.mb.customannotation.controller.*Controller.*(..)) ")
    public void logAspect() {}

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        //  获取当前类
        Class targetClass = signature.getDeclaringType();
        MethodSignature methodSignature = (MethodSignature) signature;
        //  获取当前方法
        Method method = methodSignature.getMethod();
        //  类/方法上有日志注解，则打印执行时间日志
        if (targetClass.getAnnotation(Log.class)!= null || method.getAnnotation(Log.class) != null) {
            long start = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            LOGGER.info("method:{}, process time(ms):{}", targetClass.getCanonicalName() + "#" + method.getName(), end - start);
            return result;
        }
        //  否则正常执行即可
        return joinPoint.proceed();
    }

}