package cn.mb.spitereq.controller;

import cn.mb.spitereq.annotations.AccessLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  测试controller
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/23
 */
@RestController
public class TestController {

    @GetMapping("/annotationTest")
    @AccessLimit(second = 60, maxCount = 10)
    public void annotationTest() {
        System.err.println("annotationTest");
    }

    @GetMapping("/test")
    public void test() {
        System.err.println("test");
    }



}
