package cn.mb.spitereq.interceptors;

import cn.mb.spitereq.annotations.AccessLimit;
import cn.mb.spitereq.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  恶意请求拦截器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/23
 */
@Component
public class SpiteReqInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpiteReqInterceptor.class);

    private final RedisUtil redisUtil;

    public SpiteReqInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            String ip = request.getHeader("x-forwarded-for");
            // 有可能ip是代理的
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            String prefix = "SPITE_REQ_INTERCEPTOR_";
            String key;
            Long time = 60L;
            Integer accessCountLimit = 10;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            //  带注解表示该方法限制访问次数
            if (accessLimit != null) {
                key = prefix + hm.getBeanType().getCanonicalName() + "#" + hm.getMethod().getName() + "_" + ip;
                time = Long.valueOf(accessLimit.second());
                accessCountLimit = accessLimit.maxCount();
            } else {
                //  不带注解则是对系统的访问次数，不包含对注解方法的访问次数
                key = prefix + ip;
            }
            Boolean hasKey = redisUtil.hasKey(key);
            //  1min限制访问10次
            if (!hasKey) redisUtil.setExNx(key, 0, time);
            Long count = redisUtil.incr(key);
            if (count > accessCountLimit) {
                LOGGER.info("key:{}, access count out of limit:{}", key, count);
                return false;
            }
        }
        return true;
    }
}
