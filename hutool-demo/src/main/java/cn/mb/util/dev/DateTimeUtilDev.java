package cn.mb.util.dev;

import cn.hutool.core.date.*;
import cn.hutool.core.util.ObjectUtil;

/**
 * <p>
 * 日期工具开发
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/1/25
 */
public class DateTimeUtilDev {

    public static void main(String[] args) {
        //  指定日期
        DateTime date = new DateTime(DateUtil.lastMonth());
        System.out.println("current date: " + date);
        //  指定日期周一
        DateTime thisMonday = getDayOfWeek(date, Week.MONDAY);
        System.out.println("thisMonday: " + thisMonday);
        //  指定日期周日
        DateTime thisSunday = getDayOfWeek(date, Week.SUNDAY);
        System.out.println("thisSunday: " + thisSunday);
        //  指定日期上周一
        DateTime lastMonday = DateUtil.offsetWeek(thisMonday, -1);
        System.out.println("lastMonday: " + lastMonday);
        //  指定日期上周日
        DateTime lastSunday = getDayOfWeek(lastMonday, Week.SUNDAY);
        System.out.println("lastSunday: " + lastSunday);
        //  指定日期月第一天
        DateTime monthFirstDay = getDayOfMonth(date, 1);
        System.out.println("monthFirstDay: " + monthFirstDay);
        //  指定日期月最后一天
        DateTime monthLastDay = getDayOfMonth(date, 0);
        System.out.println("monthLastDay: " + monthLastDay);
        //  获取指定日期那周的周几
        DateTime day1 = getDayOfWeekNew(date, Week.MONDAY);
        System.out.println("day1: " + day1);
        DateTime day2 = getDayOfWeekNew(date, Week.TUESDAY);
        System.out.println("day2: " + day2);
        DateTime day3 = getDayOfWeekNew(date, Week.WEDNESDAY);
        System.out.println("day3: " + day3);
        DateTime day4 = getDayOfWeekNew(date, Week.THURSDAY);
        System.out.println("day4: " + day4);
        DateTime day5 = getDayOfWeekNew(date, Week.FRIDAY);
        System.out.println("day5: " + day5);
        DateTime day6 = getDayOfWeekNew(date, Week.SATURDAY);
        System.out.println("day6: " + day6);
        DateTime day7 = getDayOfWeekNew(date, Week.SUNDAY);
        System.out.println("day7: " + day7);
        //  获取指定日期那月的第几天
        DateTime dayOfMonth1 = getDayOfMonthNew(date, 1);
        System.out.println("dayOfMonth1: " + dayOfMonth1);
        DateTime dayOfMonth0 = getDayOfMonthNew(date, 0);
        System.out.println("dayOfMonth0: " + dayOfMonth0);
    }

    /**
     * <p>
     * 获取指定日期所在月的第一天
     * </p>
     *
     * @param date 指定日期
     * @return cn.hutool.core.date.DateTime 日期所在月的第一天
     * @author guohaibin
     * @date 2021-01-25 11:26:32
     */
    public static DateTime getMonthFirstDay(DateTime date) {
        //  几号
        int dateField = date.getField(DateField.DAY_OF_MONTH);
        int offset = 1 - dateField;
        return DateUtil.offsetDay(date, offset);
    }

    /**
     * <p>
     * 获取指定日期所在月的最后一天
     * </p>
     *
     * @param date 指定日期
     * @return cn.hutool.core.date.DateTime 日期所在月的最后一天
     * @author guohaibin
     * @date 2021-01-25 11:26:32
     */
    public static DateTime getMonthLastDay(DateTime date) {
        //  几号
        int dateField = date.getField(DateField.DAY_OF_MONTH);
        //  最后一天
        int offset = date.monthEnum().getLastDay(DateUtil.isLeapYear(date.year())) - dateField;
        return DateUtil.offsetDay(date, offset);
    }

    /**
     * <p>
     * 获取指定日期所在周的周一
     * </p>
     *
     * @param date 指定日期
     * @return cn.hutool.core.date.DateTime 日期所在周的周一
     * @author guohaibin
     * @date 2021-01-25 11:26:32
     */
    public static DateTime getMonday(DateTime date) {
        //  1 - 7[1:周日;2-7:周一~周六]
        int dateField = date.getField(DateField.DAY_OF_WEEK);
        int offset = 0;
        if (dateField == 1) {
            //  1代表周日，需向前偏移6天
            offset = -6;
        } else if (dateField > 2 && dateField <= 7) {
            //  3 ~ 7代表周二至周六，需向前偏移(2 - dateField)天
            offset = 2 - dateField;
        }
        //  2代表周一，不管
        return DateUtil.offsetDay(date, offset);
    }

    /**
     * <p>
     * 获取指定日期所在周的周日
     * </p>
     *
     * @param date 指定日期
     * @return cn.hutool.core.date.DateTime 日期所在周的周日
     * @author guohaibin
     * @date 2021-01-25 11:26:32
     */
    public static DateTime getSunday(DateTime date) {
        //  1 - 7[1:周日;2-7:周一~周六]
        int dateField = date.getField(DateField.DAY_OF_WEEK);
        int offset = 0;
        if (dateField > 1 && dateField <= 7) {
            //  2 ~ 7代表周一至周六，需向后偏移(8 - dateField)天
            offset = 8 - dateField;
        }
        //  1代表周日，不管
        return DateUtil.offsetDay(date, offset);
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
        Week dateDayOfWeek = date.dayOfWeekEnum();
        int offset = 0;
        if (dateDayOfWeek != day) {
            /**
             * 周日(1)，需要后移(8 - n)天
             * 周一至周六(2~7)，移动(day.getValue() - dateDayOfWeek.getValue())天
             */
            offset = day == Week.SUNDAY ? 8 - dateDayOfWeek.getValue() : (day.getValue() - dateDayOfWeek.getValue());
        }
        //  相等表示为同一天
        return DateUtil.offsetDay(date, offset);
    }

    public static DateTime getDayOfWeekNew(DateTime date, Week day) {
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
     * 获取指定日期所在月中的第几天
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
        if (day == 0)
            day = totalDayOfMonth;
        int offset = date.dayOfMonth() == day ? 0 : (day - date.dayOfMonth());
        return DateUtil.offsetDay(date, offset);
    }

    /**
     * <p>
     * 获取指定日期所在月中的第几天（新）
     * </p>
     *
     * @param date 指定日期
     * @param day  第几天(0~31，0代表该月最后一天)
     * @return cn.hutool.core.date.DateTime 日期
     * @author guohaibin
     * @date 2021-01-26 11:40:34
     */
    public static DateTime getDayOfMonthNew(DateTime date, int day) {
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
     * 获取指定日期所在年中的第几天
     * </p>
     *
     * @param date 指定日期
     * @param day  第几天(0~366，0代表该年最后一天)
     * @return cn.hutool.core.date.DateTime 日期
     * @author guohaibin
     * @date 2021-01-26 11:40:34
     */
    public static DateTime getDayOfYearNew(DateTime date, int day) {
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
