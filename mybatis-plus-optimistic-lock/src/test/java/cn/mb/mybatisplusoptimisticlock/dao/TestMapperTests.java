package cn.mb.mybatisplusoptimisticlock.dao;

import cn.mb.mybatisplusoptimisticlock.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/10/21
 */
@SpringBootTest
public class TestMapperTests {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestService testService;

    @Test
    void insertBatchTest() {

        List<cn.mb.mybatisplusoptimisticlock.dao.entity.Test> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new cn.mb.mybatisplusoptimisticlock.dao.entity.Test(i, LocalDateTime.now()));
        }
        long l = System.currentTimeMillis();
        int mapperInsertCount = testMapper.insertBatch(list);
        long l1 = System.currentTimeMillis();
        System.out.println(String.format("mapper插入条数：%s，花费时间：%sms", mapperInsertCount, l1 - l));

        long l2 = System.currentTimeMillis();
        testService.saveBatch(list);
        long l3 = System.currentTimeMillis();
        System.out.println(String.format("service插入花费时间：%sms", l3 - l2));
    }

}
