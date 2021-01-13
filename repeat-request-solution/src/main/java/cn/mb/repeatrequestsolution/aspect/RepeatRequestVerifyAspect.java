package cn.mb.repeatrequestsolution.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.mb.repeatrequestsolution.annotation.RepeatRequestVerify;
import cn.mb.repeatrequestsolution.enums.RedisKeyEnum;
import cn.mb.repeatrequestsolution.enums.RepeatRequestVerifyMethod;
import cn.mb.repeatrequestsolution.util.RedisUtil;
import cn.mb.repeatrequestsolution.util.ReqReqHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <p>
 *  重复请求校验切面(一个注解)
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/1/7
 */
@Aspect
@Order(2)
@Component
public class RepeatRequestVerifyAspect {

    private final RedisUtil redisUtil;
    private final HttpServletRequest request;

    public RepeatRequestVerifyAspect(RedisUtil redisUtil, HttpServletRequest request) {
        this.redisUtil = redisUtil;
        this.request = request;
    }

    @Before("execution(public * cn.mb.repeatrequestsolution.controller.*Controller.*(..)) ")
    public void repeatRequestVerify(JoinPoint joinPoint) {
        System.out.println("RepeatRequestVerifyAspect");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        RepeatRequestVerify repeatRequestVerify = method.getAnnotation(RepeatRequestVerify.class);
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
                    String[] excludes = repeatRequestVerify.excludeParams();
                    RedisKeyEnum requestParamKey = RedisKeyEnum.REP_REQ_PARAM_KEY;
                    String param = ReqReqHelper.paramMD5(JSONUtil.toJsonStr(joinPoint.getArgs()[0]), excludes);
                    key = String.format(requestParamKey.getKey(), 1L, method.getName(), param);
                    ReqReqHelper.setExNxVerifyRepReq(redisUtil, key, requestParamKey.getExpireTime());
                    break;
            }
        }
    }

}
