package cn.mb.repeatrequestsolution.controller;

import cn.mb.repeatrequestsolution.response.BaseResponse;
import cn.mb.repeatrequestsolution.service.ReqSnService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  请求编号controller
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
@RestController
@RequestMapping("/reqSn")
public class ReqSnController {

    private final ReqSnService reqSnService;

    public ReqSnController(ReqSnService reqSnService) {
        this.reqSnService = reqSnService;
    }

    /**
     * <p>
     *  获取请求编号 - 获取时即设置在redis中
     * </p>
     * @return cn.mb.repeatrequestsolution.result.ResponseResult
     * @author guohaibin
     * @date 2020-11-20 11:02:42
     */
    @PostMapping("/getRequestSnAndSet2Redis")
    public BaseResponse getRequestSnAndSet2Redis() {
        return reqSnService.getRequestSnAndSet2Redis();
    }

    /**
     * <p>
     *  获取请求编号 - 使用该编号时再设置到redis中
     * </p>
     * @return cn.mb.repeatrequestsolution.result.ResponseResult
     * @author guohaibin
     * @date 2020-11-20 13:34:14
     */
    @GetMapping("/getRequestSn")
    public BaseResponse getRequestSn() {
        return reqSnService.getRequestSn();
    }
}
