package cn.mb.adminclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/9/14
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/cycle")
    public void cycle() throws Exception{
        List<Object> list = new ArrayList<>();
        for (int j = 0; j < 100000000; j++) {
            list.add(new Object());
        }
    }

    @GetMapping("/logLog")
    public void logLog() throws Exception{
        log.info("hello ****************************************");
    }
}
