package cn.mb.mybatisplusoptimisticlock.dao;

import cn.mb.mybatisplusoptimisticlock.dao.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bb
 * @since 2020-11-05
 */
public interface TestMapper extends BaseMapper<Test> {

    int insertBatch(List<Test> list);

}
