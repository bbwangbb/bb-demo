package cn.mb.repeatrequestsolution.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.mb.repeatrequestsolution.enums.RedisKeyEnum;
import cn.mb.repeatrequestsolution.response.BaseResponse;
import cn.mb.repeatrequestsolution.service.ReqSnService;
import cn.mb.repeatrequestsolution.util.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  请求凭证service.impl
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
@Service
public class ReqSnServiceImpl implements ReqSnService {

    private final RedisUtil redisUtil;

    public ReqSnServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public BaseResponse getRequestSnAndSet2Redis() {
        //  如果前端点击多次才跳转过填写订单页面，也只会带上最后那次调用的key，该接口多次调用无影响，但也需要限制次数
        RedisKeyEnum keyEnum = RedisKeyEnum.REP_REQ_SN_KEY;
        String requestSn;
        while (true) {
            requestSn = IdUtil.fastSimpleUUID();
            Boolean set = redisUtil.setExNx(requestSn, keyEnum.getValue(), keyEnum.getExpireTime());
            if (BooleanUtil.isTrue(set)) break;
        }
        return new BaseResponse(200, "成功获取请求编号！", requestSn);
    }

    @Override
    public BaseResponse getRequestSn() {
        RedisKeyEnum requestSnKey = RedisKeyEnum.REP_REQ_SN_KEY;
        Long incr = redisUtil.incr(requestSnKey.getKey());
        String requestSn = requestSnKey.getKey() + StrUtil.fillBefore(incr.toString(), '0', 10);
        return new BaseResponse(200, "成功获取请求编号！", requestSn);
    }
}
