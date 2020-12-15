package cn.mb.repeatrequestsolution.service;

import cn.mb.repeatrequestsolution.response.BaseResponse;

/**
 * <p>
 *  请求编号service
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
public interface ReqSnService {
    /**
     * <p>
     *  获取生成订单所用凭证
     *      在生成订单时，需先获取凭证，后作为参数一并传递，如凭证有效则可操作
     * </p>
     * @return java.lang.String 凭证
     * @author guohaibin
     * @date 2020-11-20 11:02:42
     */
    BaseResponse getRequestSnAndSet2Redis();

    /**
     * <p>
     *  获取请求编号
     * </p>
     * @return cn.mb.repeatrequestsolution.result.ResponseResult
     * @author guohaibin
     * @date 2020-11-20 13:34:14
     */
    BaseResponse getRequestSn();
}
