package cn.mb.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/9/9
 */
@Aspect
@Component
public class LogUtil111 {

    @Pointcut("execution(public * cn.mb.spring.bean.lifecycle.*.*(..))")
    public void pctMethod() {
    }

    @Around(value = "pctMethod()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object ret = pjp.proceed();
        System.out.println("Around advice");
        return ret;
    }

    @Before("pctMethod()")
    public void before() {
        System.out.println("Before advice");
    }

    @After(value = "pctMethod()")
    public void after() {
        System.out.println("After advice");
    }

    @AfterReturning(value = "pctMethod()")
    public void afterReturning() {
        System.out.println("AfterReturning advice");
    }

    @AfterThrowing(value = "pctMethod()")
    public void afterThrowing() {
        System.out.println("AfterThrowing advice");
    }

}
