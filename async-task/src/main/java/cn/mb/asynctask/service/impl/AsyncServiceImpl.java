package cn.mb.asynctask.service.impl;

import cn.mb.asynctask.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * <p>
 *      异步任务service实现类
 * </p>
 *
 * @author: bb
 * @createDate: 2020/11/9
 */
@Slf4j
@Service
@Async//    默认Executor
//@Async("myTaskExecutor")//  指定Executor
public class AsyncServiceImpl implements AsyncService {
    @Override
    public void testWithoutResult() {
        log.info("当前方法：{}，线程名：{}", "testWithoutResult", Thread.currentThread().getName());
        log.info("我没返回值，谢谢！");
        int i = 1 / 0;
    }

    @Override
    public Future<String> testWithResult() {
        log.info("当前方法：{}，线程名：{}", "testWithResult", Thread.currentThread().getName());
        log.info("我有返回值，谢谢！");
        int i = 1 / 0;
        return new AsyncResult<>("我是返回值！");
    }
}
