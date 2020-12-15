package cn.mb.asynctask.controller;

import cn.mb.asynctask.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: bb
 * @createDate: 2020/11/9
 */
@RestController
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * <p>
     *      调用同类中的异步任务
     * </p>
     * @param
     * @return void
     * @author guohaibin
     * @date 2020-11-09 14:47:35
     */
    @GetMapping("/callSelf")
    public void callSelf() {
        testService.callSelf();
    }

    /**
     * <p>
     *      无返回值测试
     * </p>
     * @param
     * @return void
     * @author guohaibin
     * @date 2020-11-09 14:47:35
     */
    @GetMapping("/testWithoutResult")
    public void testWithoutResult() {
        testService.testWithoutResult();
    }

    /**
     * <p>
     *      有返回值测试
     * </p>
     * @param
     * @return void
     * @author guohaibin
     * @date 2020-11-09 14:47:35
     */
    @GetMapping("/testWithResult")
    public String testWithResult() {
        return testService.testWithResult();
    }
}
