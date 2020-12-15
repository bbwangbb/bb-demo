package cn.mb.repeatrequestsolution.controller;

import cn.mb.repeatrequestsolution.annotation.ReqParamVerify;
import cn.mb.repeatrequestsolution.annotation.ReqSnVerify;
import cn.mb.repeatrequestsolution.dto.ParamDTO;
import cn.mb.repeatrequestsolution.response.BaseResponse;
import cn.mb.repeatrequestsolution.service.RepReqService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  重复请求controller
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
@RestController
@RequestMapping("/repReq")
public class RepReqController {

    private final RepReqService repReqService;

    public RepReqController(RepReqService repReqService) {
        this.repReqService = repReqService;
    }

    /**
     * <p>
     *  只检测请求编号是否可用
     * </p>
     * @return void
     * @author guohaibin
     * @date 2020-11-20 14:51:00
     */
    @ReqSnVerify
    @PostMapping("/verifyRequest")
    public void verifyRequest() {
        repReqService.verifyRequest();
    }

    /**
     * <p>
     *  通过设置进redis检测请求编号是否可用
     * </p>
     * @return void
     * @author guohaibin
     * @date 2020-11-20 14:51:00
     */
    @ReqSnVerify
    @PostMapping("/set2RedisAndVerifyRequest")
    public BaseResponse set2RedisAndVerifyRequest() {
        return repReqService.set2RedisAndVerifyRequest();
    }

    /**
     * <p>
     *  请求参数检测重复请求
     * </p>
     * @param paramDTO  参数
     * @return cn.mb.repeatrequestsolution.result.ResponseResult
     * @author guohaibin
     * @date 2020-11-20 17:16:38
     */
    @ReqParamVerify(excludeParams = {"date"})
    @PostMapping("/requestParam")
    public BaseResponse requestParam(@RequestBody ParamDTO paramDTO) {
        return repReqService.requestParam(paramDTO);
    }

}
