package cn.mb.designpatterns.责任链.filter;

import cn.mb.designpatterns.责任链.CustomFilterChain;
import cn.mb.designpatterns.责任链.ExecuteParam;

/**
 * <p>
 * 过滤器接口
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2022/1/20
 */
public interface CustomFilter {

    void doFilter(ExecuteParam executeParam, CustomFilterChain customFilterChain);

}
