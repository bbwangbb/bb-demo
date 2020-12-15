package cn.mb.asynctask.service;

/**
 * <p>
 *      测试service
 * </p>
 *
 * @author: bb
 * @createDate: 2020/11/9
 */
public interface TestService {

    void testWithoutResult();

    String testWithResult();

    void callSelf();
}
