package cn.mb.excelexport.dao.entity;

import cn.mb.excelexport.annotation.ExportField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 *  大数据量导出测试实体
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/21
 */
@Data
@AllArgsConstructor
@TableName("poi_test")
public class PoiTest {
    @TableId(type = IdType.AUTO)
    @ExportField(desc = "id")
    private Long id;
    @ExportField(desc = "description")
    private String description;

    public PoiTest(String description) {
        this.description = description;
    }
}
