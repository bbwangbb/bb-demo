package cn.mb.excelexport.task;


import java.util.List;

/**
 * <p>
 *  导入任务
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/12/31
 */
public class ImportTask<T> implements Runnable {

    private final List<T> dataList;

    public ImportTask(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void run() {
        System.out.println("current thread " + Thread.currentThread().getName());
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println(dataList.get(i));
        }
    }
}
