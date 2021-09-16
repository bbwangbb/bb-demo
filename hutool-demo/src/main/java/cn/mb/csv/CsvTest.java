package cn.mb.csv;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;

import java.io.BufferedReader;
import java.util.List;

/**
 * <p>
 * csv工具测试
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/7/16
 */
public class CsvTest {

    public static void main(String[] args) {
        BufferedReader bufferedReader = FileUtil.getUtf8Reader("E:\\Java\\items\\bb-demo\\hutool-demo\\src\\main\\resources\\test.csv");
        CsvData csvData = CsvUtil.getReader().read(bufferedReader);
        List<CsvRow> rows = csvData.getRows();
        for (int i = 0; i < rows.size(); i++) {
            System.out.println("\ncurrent line: " + (i + 1));
            CsvRow csvRow = rows.get(i);
            List<String> rawList = csvRow.getRawList();
            rawList.stream().forEach(raw -> System.out.print("\t" + raw));
        }
    }

}