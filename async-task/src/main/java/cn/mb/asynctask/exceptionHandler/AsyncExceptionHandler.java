package cn.mb.asynctask.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * <p>
 *      异步任务异常处理  -   针对无返回值任务
 * </p>
 *
 * @author: bb
 * @createDate: 2020/11/9
 */
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.info("我是专门给没返回值的异步任务处理异常的，我开始了！");
        log.info("Async method: {} has uncaught exception,params:{}", method.getName(), params);
        //  TODO 自定义操作
        log.info("Exception :" + ex.getMessage());
    }
}
