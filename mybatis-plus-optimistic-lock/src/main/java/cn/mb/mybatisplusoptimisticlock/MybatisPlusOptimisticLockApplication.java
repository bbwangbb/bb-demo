package cn.mb.mybatisplusoptimisticlock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "cn.mb.mybatisplusoptimisticlock.dao")
public class MybatisPlusOptimisticLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusOptimisticLockApplication.class, args);
    }

}
