package cn.mb.designpatterns.责任链;

import cn.mb.designpatterns.责任链.filter.RequestHeaderFilter;
import cn.mb.designpatterns.责任链.filter.RequestParamFilter;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
public class Test {

    public static void main(String[] args) {
        //  创建过滤器链
        CustomFilterChain filterChain = new CustomFilterChain();
        //  创建过滤器
        RequestParamFilter requestParamFilter = new RequestParamFilter();
        RequestHeaderFilter requestHeaderFilter = new RequestHeaderFilter();
        //  将过滤器添加到过滤器链中
        filterChain.addFilter(requestParamFilter);
        filterChain.addFilter(requestHeaderFilter);
        //  设置参数等
        ExecuteParam executeParam = new ExecuteParam();
        executeParam.setCheckHeader(true);
        //  执行过滤器链
        filterChain.doFilter(executeParam, filterChain);
    }

}
