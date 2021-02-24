package cn.mb.itemdemo.component;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/24
 */
@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

    //  所有资源
    private List<String> allResource;

    @PostConstruct
    public void loadDataSource() {
        //  把所有权限加载到内存中
        allResource = new ArrayList<>();
        allResource.add("/add");
        allResource.add("/delete");
        allResource.add("/update");
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        List<ConfigAttribute>  configAttributes = new ArrayList<>();
        //  获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        if (allResource.contains(url)) {
            configAttributes.add(new org.springframework.security.access.SecurityConfig(url));
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
