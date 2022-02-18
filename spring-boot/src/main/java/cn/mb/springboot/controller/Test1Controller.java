package cn.mb.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/12
 */
@RestController
@RequestMapping("/test")
public class Test1Controller {

    @Value("${bb.name}")
    private String name;
    @Value("${bb.age}")
    private String age;

    @GetMapping("/test")
    public void test() {
        System.out.println(name);
        System.out.println(age);
    }
}
