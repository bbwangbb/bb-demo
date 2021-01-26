package cn.mb.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.ObjectUtil;

/**
 * <p>
 *  日期工具类
 * </p>
 *
 * @author: bb
 * @createDate: 2021/1/25
 */
public class DateTimeUtil {

    public static void main(String[] args) {
        DateTime date = new DateTime(DateUtil.lastWeek());
        System.out.println(getDayOfWeek(date, Week.MONDAY));
        System.out.println(getDayOfWeek(date, Week.TUESDAY));
        System.out.println(getDayOfWeek(date, Week.WEDNESDAY));
        System.out.println(getDayOfWeek(date, Week.THURSDAY));
        System.out.println(getDayOfWeek(date, Week.FRIDAY));
        System.out.println(getDayOfWeek(date, Week.SATURDAY));
        System.out.println(getDayOfWeek(date, Week.SUNDAY));
        System.out.println("------------------------------------");
        System.out.println(getDayOfMonth(date, 1));
        System.out.println(getDayOfMonth(date, 0));
//        System.out.println(getDayOfMonth(date, -1));
//        System.out.println(getDayOfMonth(date, 32));
        System.out.println(getDayOfMonth(date, 15));
        System.out.println("------------------------------------");
        System.out.println(getDayOfYear(date, 1));
        System.out.println(getDayOfYear(date, 0));
//        System.out.println(getDayOfYear(date, -1));
//        System.out.println(getDayOfYear(date, 367));
        System.out.println(getDayOfYear(date, 150));
    }

    /**
     * <p>
     * 获取指定日期所在周的周几日期(周日至周六 == 1至7)
     * </p>
     *
     * @param date 指定日期
     * @param day  周几
     * @return cn.hutool.core.date.DateTime 日期
     * @author guohaibin
     * @date 2021-01-26 11:17:13
     */
    public static DateTime getDayOfWeek(DateTime date, Week day) {
        if (ObjectUtil.isNull(date) || ObjectUtil.isNull(day))
            throw new IllegalArgumentException("date or day is null");
        Week dateWeek = date.dayOfWeekEnum();
        if (dateWeek == day) return date;
        if (day == Week.MONDAY) return DateUtil.beginOfWeek(date);
        if (day == Week.SUNDAY) return DateUtil.endOfWeek(date);
        return DateUtil.offsetDay(date, day.getValue() - dateWeek.getValue());
    }

    /**
     * <p>
     * 获取指定日期所在月中的第几天(0-31)
     * </p>
     *
     * @param date 指定日期
     * @param day  第几天(0~31，0代表该月最后一天)
     * @return cn.hutool.core.date.DateTime 日期
     * @author guohaibin
     * @date 2021-01-26 11:40:34
     */
    public static DateTime getDayOfMonth(DateTime date, int day) {
        if (ObjectUtil.isNull(date))
            throw new IllegalArgumentException("date is null");
        if (day < 0 || day > 31)
            throw new IllegalArgumentException("day is between 0 and 31");
        Month month = date.monthEnum();
        int totalDayOfMonth = month.getLastDay(date.isLeapYear());
        if (day > totalDayOfMonth) {
            throw new IllegalArgumentException(totalDayOfMonth + " days at most in " + month);
        }
        if (date.dayOfMonth() == day) return date;
        if (day == 0) return DateUtil.endOfMonth(date);
        if (day == 1) return DateUtil.beginOfMonth(date);
        return DateUtil.offsetDay(date, day - date.dayOfMonth());
    }

    /**
     * <p>
     * 获取指定日期所在年中的第几天(0-366)
     * </p>
     *
     * @param date 指定日期
     * @param day  第几天(0~366，0代表该年最后一天)
     * @return cn.hutool.core.date.DateTime 日期
     * @author guohaibin
     * @date 2021-01-26 11:40:34
     */
    public static DateTime getDayOfYear(DateTime date, int day) {
        if (ObjectUtil.isNull(date))
            throw new IllegalArgumentException("date is null");
        if (day < 0 || day > 366)
            throw new IllegalArgumentException("day is between 0 and 366");
        int lengthOfYear = DateUtil.lengthOfYear(date.year());
        if (day > lengthOfYear) {
            throw new IllegalArgumentException(lengthOfYear + " days at most in " + date.year());
        }
        if (day == 1) return DateUtil.beginOfYear(date);
        if (day == 0) return DateUtil.endOfYear(date);
        return DateUtil.offsetDay(date, day - date.dayOfYear());
    }


}
