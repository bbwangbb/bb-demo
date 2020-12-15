package cn.mb.repeatrequestsolution.service;

import cn.mb.repeatrequestsolution.dto.ParamDTO;
import cn.mb.repeatrequestsolution.response.BaseResponse;

/**
 * <p>
 *  重复请求service
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
public interface RepReqService {
    /**
     * <p>
     *  只检测请求编号是否可用
     * </p>
     * @return void
     * @author guohaibin
     * @date 2020-11-20 14:51:00
     */
    BaseResponse verifyRequest();

    /**
     * <p>
     *  通过设置进redis检测请求编号是否可用
     * </p>
     * @return void
     * @author guohaibin
     * @date 2020-11-20 14:51:00
     */
    BaseResponse set2RedisAndVerifyRequest();

    BaseResponse requestParam(ParamDTO paramDTO);
}
