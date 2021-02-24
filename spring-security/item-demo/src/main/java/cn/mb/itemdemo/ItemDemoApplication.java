package cn.mb.itemdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.mb.itemdemo.dao")
public class ItemDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemDemoApplication.class, args);
    }

}
