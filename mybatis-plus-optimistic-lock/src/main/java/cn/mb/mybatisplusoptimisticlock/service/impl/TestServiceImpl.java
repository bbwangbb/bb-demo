package cn.mb.mybatisplusoptimisticlock.service.impl;

import cn.mb.mybatisplusoptimisticlock.dao.entity.Test;
import cn.mb.mybatisplusoptimisticlock.dao.TestMapper;
import cn.mb.mybatisplusoptimisticlock.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bb
 * @since 2020-11-05
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    /**
     * 不加@Transactional注解
     *  结果：可以满足乐观锁，因为允许脏读、幻读、不可重复读，但是如果其他地方调用该方法，无法回滚
     */
    @Override
    public void test1() {
        while (true) {
            Test test = getById(1);
            test.setNum(test.getNum() + 1);
            boolean result = updateById(test);
            if (result) break;
        }
    }

    /**
     * 加@Transactional
     *      1、不指定隔离级别(mysql默认隔离级别为可重复读)/isolation = Isolation.REPEATABLE_READ
     *          该隔离级别，可以避免脏读和不可重复读，
     *          而由于可以避免不可重复读的情况，同样条件读取出来的数据永远不会变，
     *          因此导致乐观锁即使修改失败，也不会读取数据库修改后的数据
     *
     *      2、指定隔离级别[READ_UNCOMMITTED/READ_COMMITTED/SERIALIZABLE]
     *          READ_UNCOMMITTED：相当于加@Transactional只能保证事务，但由于无法避免脏读、幻读、不可重复读，所以不考虑
     *          SERIALIZABLE：相当于排队操作事务中的数据，肯定能保证乐观锁
     *          READ_COMMITTED：可避免脏读，但允许幻读和不可重复读
     *              该级别下，可以避免读取脏数据，而又允许不可重复读发生，所以乐观锁修改失败后，会再次从数据库中读数据
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void test2() {
        while (true) {
            Test test = getById(2);
            test.setNum(test.getNum() + 1);
            boolean result = updateById(test);
            if (result) break;
        }
    }

}
