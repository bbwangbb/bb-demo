package cn.mb.mybatisplusoptimisticlock.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author bb
 * @since 2020-11-05
 */
@Data
@Accessors(chain = true)
@TableName(value = "test", resultMap = "BaseResultMap")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer num;

    /**
     * 乐观锁支持格式：int,Integer,long,Long,Date,Timestamp,LocalDateTime
     * 但如果是时间戳，其实是可以一样的，比如两个事务都在同一秒内修改了数据，也会相互覆盖，因此我选择用数字，这样铁定自增
     */
    private LocalDateTime updateTime;

    @Version
    private Integer version;

}
