package cn.mb.mybatisplusoptimisticlock.controller;


import cn.mb.mybatisplusoptimisticlock.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bb
 * @since 2020-11-05
 */
@Validated
@RestController
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public void test(Long id) {
        if (id == 1) {
            testService.test1();
        } else if (id == 2) {
            testService.test2();
        }
    }

}
