package cn.mb.util;

import cn.hutool.core.util.ObjectUtil;

import java.math.BigDecimal;

/**
 * <p>
 *  百分比工具类
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/1/25
 */
public class PercentUtil {

    public static void main(String[] args) {
        PercentUtil percentUtil = new PercentUtil();
        BigDecimal a = new BigDecimal("0.5");
        BigDecimal b = new BigDecimal("1");
        String percent = percentUtil.calcPercent(a, b);
        //  不变
        System.out.println(percentUtil.calcPercent(BigDecimal.valueOf(0), BigDecimal.valueOf(0)));
        //  增长
        System.out.println(percentUtil.calcPercent(BigDecimal.valueOf(0), BigDecimal.valueOf(1)));
        System.out.println(percentUtil.calcPercent(BigDecimal.valueOf(0.5), BigDecimal.valueOf(1)));
        //  降低
        System.out.println(percentUtil.calcPercent(BigDecimal.valueOf(1), BigDecimal.valueOf(0.4)));
        System.out.println(percentUtil.calcPercent(null, BigDecimal.valueOf(10)));
        System.out.println(percentUtil.calcPercent(BigDecimal.valueOf(1), null));
        System.out.println(percentUtil.calcPercent(null, null));
    }

    public String calcPercent(BigDecimal preNum, BigDecimal sufNum) {
        double result = countDecimal(preNum, sufNum);
        if (result > 0) {
            return "+" + result + "%";
        }
        if (result < 0) {
            return result + "%";
        }
        if (result == 0) {
            return "+" + 0 + "%";
        }
        return null;
    }

    public double countDecimal(BigDecimal preNum, BigDecimal sufNum) {
        boolean isPreNullOrZero = isNullOrZero(preNum);
        boolean isSufNullOrZero = isNullOrZero(sufNum);
        //  都不为null | 0
        if (!isPreNullOrZero && !isSufNullOrZero) {
            boolean isEqual = ObjectUtil.equal(preNum, sufNum);
            if (isEqual == false) {
                return calc(preNum, sufNum);
            }
            if (isEqual) {
                return 0;
            }
        } else if (isPreNullOrZero && isSufNullOrZero) {
            //  preNum = 0, sufNu = 0
            return 0;
        }  else if (isSufNullOrZero) {
            return 100;
        } else if(isPreNullOrZero) {
            return -100;
        }
        return 0;
    }

    //  验证数字是否为零和null
    public boolean isNullOrZero(BigDecimal num) {
        if (null != num && num.compareTo(BigDecimal.ZERO) != 0) {
            return false;
        }
        return true;
    }

    //  真正计算
    public double calc(BigDecimal preNum, BigDecimal sufNum) {
        //  (前面的数字-后面的数字)/后面的数字*100
        BigDecimal bigDecimal = (preNum.subtract(sufNum)).divide(sufNum).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_UP);
        if (bigDecimal.compareTo(BigDecimal.ZERO) != 0) {
            return bigDecimal.doubleValue();
        }
        return 0;
    }

}


