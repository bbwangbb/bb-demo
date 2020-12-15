package cn.mb.asynctask.service.impl;

import cn.mb.asynctask.service.AsyncService;
import cn.mb.asynctask.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * <p>
 *      测试service实现类
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/9
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    private final AsyncService asyncService;

    public TestServiceImpl(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @Override
    public void testWithoutResult() {
        asyncService.testWithoutResult();
    }

    @Override
    public String testWithResult() {
        try {
            Future<String> result = asyncService.testWithResult();
            return result.get();
        } catch (Exception e) {
            log.error("Exception：{}", e.getMessage());
        }
        return null;
    }

    @Override
    public void callSelf() {
        log.info("当前方法：{}，线程名：{}", "callSelf", Thread.currentThread().getName());
        task();
    }

    @Async
    public void task() {
        log.info("当前方法：{}，线程名：{}", "task", Thread.currentThread().getName());
    }
}
