package cn.mb.asynctask.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * <p>
 *      异步任务service
 * </p>
 *
 * @author: bb
 * @createDate: 2020/11/9
 */
@Service
public interface AsyncService {

    void testWithoutResult();

    Future<String> testWithResult();

}
