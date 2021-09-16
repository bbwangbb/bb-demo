package cn.mb.spring.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AopTest {

    @Autowired
    private AopTestDemo aopTestDemo;

    @Test
    void test() {
        aopTestDemo.say("hi");
    }

}
