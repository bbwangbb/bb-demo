package cn.mb.designpatterns.责任链.filter;

import cn.mb.designpatterns.责任链.CustomFilterChain;
import cn.mb.designpatterns.责任链.ExecuteParam;

/**
 * <p>
 * 请求头过滤器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
public class RequestHeaderFilter implements CustomFilter {
    @Override
    public void doFilter(ExecuteParam executeParam,  CustomFilterChain customFilterChain) {
        if (executeParam.isCheckHeader()) {
            System.out.println("请求头过滤器");
        }
        customFilterChain.doFilter(executeParam, customFilterChain);
    }
}
