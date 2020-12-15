package cn.mb.customannotation.controller;

import cn.mb.customannotation.annotation.Log;
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
@Log
@RestController
public class TestController {

//    @Log
    @GetMapping("/logTest")
    public void logTest() {
        System.err.println("logTest");
    }

    @GetMapping("/noLogTest")
    public void noLogTest() {
        System.err.println("noLogTest");
    }
}
