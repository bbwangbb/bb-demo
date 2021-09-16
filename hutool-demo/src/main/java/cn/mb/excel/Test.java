package cn.mb.excel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.FileInputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/7/30
 */
public class Test {

    private static final long 首保有效月 = 6;

    public static void main(String[] args) throws Exception {
        首保模拟();
    }


    private static void 二保模拟() throws Exception {
        ExcelReader 首保单Reader = ExcelUtil.getReader(new FileInputStream("C:\\Users\\HUAWEI\\Desktop\\首保单.xlsx"));
        ExcelReader 维修记录Reader = ExcelUtil.getReader(new FileInputStream("C:\\Users\\HUAWEI\\Desktop\\维修记录.xlsx"));
        //  用首保单比对维修记录
    }

    private static void 首保模拟() throws Exception {
        ExcelReader 回厂信息Reader = ExcelUtil.getReader(new FileInputStream("C:\\Users\\HUAWEI\\Desktop\\回厂信息.xlsx"));
        回厂信息Reader.addHeaderAlias("委托书号", "tpNo");
        回厂信息Reader.addHeaderAlias("客户名称", "name");
        回厂信息Reader.addHeaderAlias("手机号", "phone");
        回厂信息Reader.addHeaderAlias("底盘号", "vin");
        List<回厂信息> 回厂信息List = 回厂信息Reader.readAll(回厂信息.class);
        List<String> 回厂信息vinList = 回厂信息List.stream().map(回厂信息::getVin).collect(Collectors.toList());

        ExcelReader 车辆档案Reader = ExcelUtil.getReader(new FileInputStream("C:\\Users\\HUAWEI\\Desktop\\车辆档案.xlsx"));
        车辆档案Reader.addHeaderAlias("车主姓名", "name");
        车辆档案Reader.addHeaderAlias("车辆VIN码", "vin");
        车辆档案Reader.addHeaderAlias("销售日期", "saleDate");
        车辆档案Reader.addHeaderAlias("客户档案号", "sn");
        List<车辆档案> 车辆档案List = 车辆档案Reader.readAll(车辆档案.class);

        System.out.println("回厂信息List：" + 回厂信息List);
        System.out.println("车辆档案List：" + 车辆档案List);
        System.out.println("---------------------------------------------------");

        List<车辆档案> 需要首保车辆List = new ArrayList<>();

        Date today = new Date();
        车辆档案List.stream().forEach(车辆档案 -> {
            System.out.println(车辆档案);
            //  已经首保过的不管
            if (回厂信息vinList.contains(车辆档案.getVin())) {
                return;
            }
            //  销售日期和今日做月份差
            Date saleDate = Date.from(车辆档案.getSaleDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            long intervalMonth = DateUtil.betweenMonth(saleDate, today, true);
            System.out.println("销售日期：" + 车辆档案.getSaleDate() + "\t今日日期：" + today + "\t月份差：" + intervalMonth);
            if (intervalMonth >= 首保有效月) {
                需要首保车辆List.add(车辆档案);
            }
        });
        System.out.println("---------------------------------------------------");
        System.out.println("需要首保的车辆：" + 需要首保车辆List);

        //  插入数据

    }

}

