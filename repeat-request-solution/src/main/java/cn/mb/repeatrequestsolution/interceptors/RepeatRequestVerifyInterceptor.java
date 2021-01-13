package cn.mb.repeatrequestsolution.interceptors;

import cn.hutool.core.util.ObjectUtil;
import cn.mb.repeatrequestsolution.annotation.RepeatRequestVerify;
import cn.mb.repeatrequestsolution.bean.MyHttpServletRequestWrapper;
import cn.mb.repeatrequestsolution.enums.RedisKeyEnum;
import cn.mb.repeatrequestsolution.enums.RepeatRequestVerifyMethod;
import cn.mb.repeatrequestsolution.util.RedisUtil;
import cn.mb.repeatrequestsolution.util.ReqReqHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  重复请求拦截器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/1/13
 */
@Component
public class RepeatRequestVerifyInterceptor implements HandlerInterceptor {

    private final RedisUtil redisUtil;

    public RepeatRequestVerifyInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("RepeatRequestVerifyInterceptor");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RepeatRequestVerify repeatRequestVerify = handlerMethod.getMethodAnnotation(RepeatRequestVerify.class);
            if (ObjectUtil.isNotNull(repeatRequestVerify)) {
                RepeatRequestVerifyMethod repeatRequestVerifyMethod = repeatRequestVerify.method();
                String key;
                switch (repeatRequestVerifyMethod) {
                    case REQUEST_SN:
                        key = request.getHeader("REQ_SN");
                        ReqReqHelper.setExNxVerifyRepReq(redisUtil, key, RedisKeyEnum.REP_REQ_PARAM_KEY.getExpireTime());
//                        ReqReqHelper.setExVerifyRepReq(redisUtil, key);
                        break;
                    case REQUEST_PARAMS:
                        MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(request);
                        String body = requestWrapper.getBody();
                        String[] excludes = repeatRequestVerify.excludeParams();
                        RedisKeyEnum requestParamKey = RedisKeyEnum.REP_REQ_PARAM_KEY;
                        String param = ReqReqHelper.paramMD5(body, excludes);
                        key = String.format(requestParamKey.getKey(), 1L, handlerMethod.getMethod().getName(), param);
                        ReqReqHelper.setExNxVerifyRepReq(redisUtil, key, requestParamKey.getExpireTime());
                        break;
                }
            }
        }

        return false;
    }
}