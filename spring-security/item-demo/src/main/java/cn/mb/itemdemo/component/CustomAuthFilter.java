package cn.mb.itemdemo.component;

import cn.mb.itemdemo.config.IgnoreUrlsConfig;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 *  自定义鉴权过滤器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/24
 */
@Component
public class CustomAuthFilter extends AbstractSecurityInterceptor implements Filter {

    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final CustomMetadataSource customMetadataSource;

    public CustomAuthFilter(IgnoreUrlsConfig ignoreUrlsConfig, CustomMetadataSource customMetadataSource, CustomAccessDecisionManager customAccessDecisionManager) {
        this.ignoreUrlsConfig = ignoreUrlsConfig;
        this.customMetadataSource = customMetadataSource;
        super.setAccessDecisionManager(customAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        //  OPTIONS请求直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS.toString())) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            return;
        }
        //  白名单请求直接放行
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String path : ignoreUrlsConfig.getUrls()) {
            if (pathMatcher.match(path, request.getRequestURI())) {
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
                return;
            }
        }
        //  此处会调用AccessDecisionManager中的decide方法进行鉴权操作
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return customMetadataSource;
    }
}
