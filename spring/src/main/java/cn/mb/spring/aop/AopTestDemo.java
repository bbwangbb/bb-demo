package cn.mb.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class AopTestDemo {

    public void say(String word) {
        System.out.println(word);
    }

}
