package cn.mb.spring.bean.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/15
 */
@SpringBootTest
public class LifeCycleBeanTest {

    @Autowired
    private LifeCycleBean lifeCycleBean;

    @Test
    void test() {
        lifeCycleBean.hello();
    }

}
