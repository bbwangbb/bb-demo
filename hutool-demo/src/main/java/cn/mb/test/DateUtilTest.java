package cn.mb.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.mb.util.DateTimeUtil;

/**
 * <p>
 *  DateUtil测试类
 * </p>
 *
 * @author: bb
 * @createDate: 2021/1/26
 */
public class DateUtilTest {
    public static void main(String[] args) {
        //  当前日期
        DateTime today = DateUtil.date();
        DateTime yesterday1 = DateUtil.yesterday();
        DateTime yesterday2 = DateUtil.offsetDay(today, -1);
        System.out.println("昨天：" + yesterday1 + "， " + yesterday2);
        DateTime tomorrow1 = DateUtil.tomorrow();
        DateTime tomorrow2 = DateUtil.offsetDay(today, 1);
        System.out.println("明天：" + tomorrow1 + "， " + tomorrow2);
        DateTime lastWeekToday1 = DateUtil.lastWeek();
        DateTime lastWeekToday2 = DateUtil.offsetWeek(today, -1);
        System.out.println("上周今天：" + lastWeekToday1 + "， " + lastWeekToday2);
        DateTime lastMonthToday1 = DateUtil.lastMonth();
        DateTime lastMonthToday2 = DateUtil.offsetMonth(today, -1);
        System.out.println("上月今天：" + lastMonthToday1 + "， " + lastMonthToday2);
        DateTime beginOfYear = DateUtil.beginOfYear(today);
        System.out.println("年第一天：" + beginOfYear);
        DateTime endOfYear = DateUtil.endOfYear(today);
        System.out.println("年最后一天：" + endOfYear);
        DateTime beginOfMonth = DateUtil.beginOfMonth(today);
        System.out.println("月第一天：" + beginOfMonth);
        DateTime endOfMonth = DateUtil.endOfMonth(today);
        System.out.println("月最后一天：" + endOfMonth);
        DateTime beginOfWeek = DateUtil.beginOfWeek(today);
        System.out.println("周第一天：" + beginOfWeek);
        DateTime endOfWeek = DateUtil.endOfWeek(today);
        System.out.println("周最后一天：" + endOfWeek);
        DateTime dayOfWeek = DateTimeUtil.getDayOfWeek(today, Week.FRIDAY);
        System.out.println("周某天：" + dayOfWeek);
        DateTime dayOfMonth = DateTimeUtil.getDayOfMonth(today, 15);
        System.out.println("月某天：" + dayOfMonth);
        DateTime dayOfYear = DateTimeUtil.getDayOfYear(today, 150);
        System.out.println("年某天：" + dayOfYear);

        //  时间差
        long 天数差 = DateUtil.between(DateUtil.yesterday(), DateUtil.tomorrow(), DateUnit.DAY);
        System.out.println("天数差：" + 天数差);
        long 小时差 = DateUtil.between(DateUtil.yesterday(), DateUtil.tomorrow(), DateUnit.HOUR);
        System.out.println("小时差：" + 小时差);

        //  判断闰年
        boolean isLeapYear = DateUtil.isLeapYear(2021);
        System.out.println("2021是否为闰年：" + isLeapYear);
    }
}
