package cn.mb.itemdemo.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * <p>
 *  只注入AccessDecisionManager实现鉴权
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/25
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //  url即为资源名
            String url = ((FilterInvocation) object).getRequestUrl();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            //  如果有该权限直接放行
            if (url.trim().equals(grantedAuthority.getAuthority())) {
                return;
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
