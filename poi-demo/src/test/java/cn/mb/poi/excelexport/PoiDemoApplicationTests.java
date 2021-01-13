package cn.mb.poi.excelexport;

import cn.mb.poi.excelexport.dao.entity.PoiTest;
import cn.mb.poi.excelexport.service.PoiTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class PoiDemoApplicationTests {

    @Autowired
    private PoiTestService poiTestService;

    @Test
    void saveBatch() {
        /**
         * TODO
         *  1   执行sql
         *  2   插入数据
         *  3   测试
         */
        final int size = 10000;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 200, 3000, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
        for (int i = 0; i < 100; i++) {
            final int count = i * size;
            List<PoiTest> poiTestList = new ArrayList<>(count);
            for (int j = 1; j <= size; j++) {
                poiTestList.add(new PoiTest("" + (count + j)));
            }
            poiTestService.saveBatch(poiTestList);
        }
    }

}
