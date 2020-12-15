package cn.mb.asynctask.config;

import cn.mb.asynctask.exceptionHandler.AsyncExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * <p>
 *      异步任务配置
 *          1、线程池配置
 *          2、异步任务异常处理  -   针对无返回值任务
 * </p>
 *
 * @author: bb
 * @createDate: 2020/11/9
 */
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    //  注入方式设置线程池
//    @Bean("myTaskExecutor")
//    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setThreadNamePrefix("AsyncExecutorThreadByBean-");
//        executor.setCorePoolSize(10);
//        executor.setMaxPoolSize(100);
//        executor.setQueueCapacity(100);
//        return executor;
//    }

    //  实现AsyncConfigurer接口方式设置线程池
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncExecutorThreadByOverride-");
        executor.initialize(); //如果不初始化，导致找到不到执行器
        return executor;
    }

    //  异步任务异常处理  -   针对无返回值任务
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }
}
