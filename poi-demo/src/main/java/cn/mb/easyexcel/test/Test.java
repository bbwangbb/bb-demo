package cn.mb.easyexcel.test;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/14
 */
public class Test {

    public static void main(String[] args) {
//        读();
        普通写();
    }

    private static void 普通写() {
        String fileName = "C:\\Users\\HUAWEI\\Desktop\\test.xls";
        ArrayList<String> strings = CollUtil.toList("1", "2");
        List<A> aList = CollUtil.toList(
                new A("1", "a", "1", "item"),
                new A("1", "b", "2", "item2"),
                new A("3", "c", "3", "item3")
        );
//        EasyExcel.write(fileName, A.class).sheet("模板")
//                .registerWriteHandler(new LoopMergeStrategy(2, 1))
//                .doWrite(aList);
        EasyExcel.write(fileName, A.class).sheet("模板")
                .registerWriteHandler(new CustomRowWriteHandler(1, 0, CollUtil.toList(2, 1)))
                .doWrite(aList);
    }

    private static void 读() {
        String fileName = "C:\\Users\\HUAWEI\\Desktop\\订单数据.xls";
        EasyExcel.read(fileName, A.class, new PageReadListener<A>(dataList -> {
            for (A a : dataList) {
                System.out.println(a);
            }
        })).sheet().doRead();
    }

}
