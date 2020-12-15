package cn.mb.repeatrequestsolution.service.impl;

import cn.mb.repeatrequestsolution.dto.ParamDTO;
import cn.mb.repeatrequestsolution.response.BaseResponse;
import cn.mb.repeatrequestsolution.service.RepReqService;
import cn.mb.repeatrequestsolution.util.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  重复请求service.impl
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
@Service
public class RepReqServiceImpl implements RepReqService {

    private final RedisUtil redisUtil;

    public RepReqServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public BaseResponse verifyRequest() {
        return new BaseResponse(200, "成功请求");
    }

    @Override
    public BaseResponse set2RedisAndVerifyRequest() {
        return new BaseResponse(200, "成功请求");
    }

    @Override
    public BaseResponse requestParam(ParamDTO paramDTO) {
        return new BaseResponse(200, "成功请求");
    }
}
