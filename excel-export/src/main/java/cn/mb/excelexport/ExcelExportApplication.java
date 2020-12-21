package cn.mb.excelexport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.mb.excelexport.dao")
public class ExcelExportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelExportApplication.class, args);
    }

}
