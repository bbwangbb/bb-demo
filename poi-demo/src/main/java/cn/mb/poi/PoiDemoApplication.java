package cn.mb.poi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.mb.poi.excelexport.dao")
public class PoiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoiDemoApplication.class, args);
    }

}