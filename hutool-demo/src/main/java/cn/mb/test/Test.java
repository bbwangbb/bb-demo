package cn.mb.test;

import cn.hutool.core.util.StrUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/5/27
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(StrUtil.startWithIgnoreCase("s", "S"));
        System.out.println(StrUtil.startWithIgnoreCase("s（1111）", "S"));
    }
}
