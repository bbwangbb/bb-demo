package cn.mb.mybatisplusoptimisticlock.service;

import cn.mb.mybatisplusoptimisticlock.dao.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bb
 * @since 2020-11-05
 */
public interface TestService extends IService<Test> {

    void test1();

    void test2();

}
