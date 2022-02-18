package cn.mb.easyexcel.test;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.handler.context.RowWriteHandlerContext;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/18
 */
@Data
@NoArgsConstructor
public class CustomRowWriteHandler implements RowWriteHandler {

    /**
     * 数据首行
     */
    private int dataFirstRowIndex;

    /**
     * 当前位置
     */
    private int currentIndex;

    /**
     * 合并索引列表
     */
    private List<Integer> indexList;

    public CustomRowWriteHandler(int dataFirstRowIndex, int currentIndex, List<Integer> indexList) {
        this.dataFirstRowIndex = dataFirstRowIndex;
        this.currentIndex = currentIndex;
        this.indexList = indexList;
    }

    @Override
    public void afterRowDispose(RowWriteHandlerContext context) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(
                    1,
                    2,
                    0, 0);
            currentIndex++;
            context.getWriteSheetHolder().getSheet().addMergedRegionUnsafe(cellRangeAddress);
//        Integer relativeRowIndex = context.getRelativeRowIndex();
//        if (context.getHead() || relativeRowIndex == null) {
//            return;
//        }
//        if (currentIndex >= indexList.size()) {
//            return;
//        }
//        Integer count = indexList.get(currentIndex);
//        if (relativeRowIndex % count == 0) {
//            Integer rowIndex = context.getRowIndex();
//            CellRangeAddress cellRangeAddress = new CellRangeAddress(
//                    rowIndex,
//                    rowIndex + count,
//                    0, 0);
//            currentIndex++;
//            context.getWriteSheetHolder().getSheet().addMergedRegionUnsafe(cellRangeAddress);
//        }
    }

}
