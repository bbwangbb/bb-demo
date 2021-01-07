package cn.mb.repeatrequestsolution.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.mb.repeatrequestsolution.annotation.ReqParamVerify;
import cn.mb.repeatrequestsolution.annotation.ReqSnVerify;
import cn.mb.repeatrequestsolution.enums.RedisKeyEnum;
import cn.mb.repeatrequestsolution.util.RedisUtil;
import cn.mb.repeatrequestsolution.util.ReqReqHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <p>
 *  重复请求校验切面(两个注解)
 *      请求编号检测
 *      请求参数检测
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
@Aspect
@Order(3)
@Component
public class RepReqVerifyAspect {

    private final HttpServletRequest request;
    private final RedisUtil redisUtil;

    public RepReqVerifyAspect(HttpServletRequest request, RedisUtil redisUtil) {
        this.request = request;
        this.redisUtil = redisUtil;
    }

    @Pointcut("execution(public * cn.mb.repeatrequestsolution.controller.*Controller.*(..)) ")
    public void aspect() {
    }

    @Around("aspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        ReqSnVerify annotation = method.getAnnotation(ReqSnVerify.class);
        if (annotation != null) {
            //  请求编号检测方式
            String key = request.getHeader("REQ_SN");
            if (StrUtil.isNotEmpty(key)) {

                /**
                 * 方式一
                 *  编号获取时不设置在redis中
                 *  在使用前,再set key val ex nx
                 *  设置成功则可操作,失败则为重复请求
                 *
                 * 问题
                 *  同一个编号,set redis后,过了过期时间,还可以再次set
                 *
                 * 优势
                 *  判断简单
                 */
                ReqReqHelper.setExNxVerifyRepReq(redisUtil, key, RedisKeyEnum.REP_REQ_PARAM_KEY.getExpireTime());

                /**
                 * 方式二
                 *  获取编号时就set redis中且初始值为0
                 *  先从redis中get,无则无法操作
                 *  有则自增,自增为1,则可以操作,非1说明重复操作
                 *  且值到达一定值,则可对用户做违规请求处理
                 *
                 * 问题
                 *  逻辑相比上面稍多
                 *
                 * 优势
                 *  一个key即使过了过期时间,也无法再次请求
                 */
//                ReqReqHelper.setExVerifyRepReq(redisUtil, key);
            }
        } else {
            //  请求参数检测方式
            ReqParamVerify reqParamVerify = method.getAnnotation(ReqParamVerify.class);
            if (reqParamVerify != null) {
                String[] excludes = reqParamVerify.excludeParams();
                RedisKeyEnum requestParamKey = RedisKeyEnum.REP_REQ_PARAM_KEY;
                String param = ReqReqHelper.paramMD5(JSONUtil.toJsonStr(joinPoint.getArgs()[0]), excludes);
                String key = String.format(requestParamKey.getKey(), 1L, method.getName(), param);
                ReqReqHelper.setExNxVerifyRepReq(redisUtil, key, requestParamKey.getExpireTime());
            }
        }
        return joinPoint.proceed();
    }

}
