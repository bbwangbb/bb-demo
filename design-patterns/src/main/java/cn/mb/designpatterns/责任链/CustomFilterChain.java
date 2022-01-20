package cn.mb.designpatterns.责任链;

import cn.mb.designpatterns.责任链.filter.CustomFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自定义过滤器链
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
public class CustomFilterChain implements CustomFilter {

    /**
     * 第一个过滤器位置
     */
    private int pos = 0;

    private List<CustomFilter> filters;

    public void addFilter(CustomFilter customFilter) {
        if (filters == null) {
            filters = new ArrayList<>();
        }

        filters.add(customFilter);
    }

    @Override
    public void doFilter(ExecuteParam executeParam, CustomFilterChain customFilterChain) {
        if (pos == filters.size()) {
            System.out.println("过滤器没了");
            return;
        }
        CustomFilter customFilter = filters.get(pos++);
        customFilter.doFilter(executeParam, customFilterChain);
    }
}
