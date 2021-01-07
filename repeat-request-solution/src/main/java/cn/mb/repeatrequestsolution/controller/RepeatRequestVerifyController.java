package cn.mb.repeatrequestsolution.controller;

import cn.mb.repeatrequestsolution.annotation.RepeatRequestVerify;
import cn.mb.repeatrequestsolution.dto.ParamDTO;
import cn.mb.repeatrequestsolution.enums.RepeatRequestVerifyMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  重复请求注解测试接口
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/1/7
 */
@RestController
@RequestMapping("/repeatRequestVerify")
public class RepeatRequestVerifyController {

    @GetMapping("/test")
    @RepeatRequestVerify(method = RepeatRequestVerifyMethod.REQUEST_PARAMS, excludeParams = {"date"})
    public String test(@RequestBody ParamDTO paramDTO) {
        return "hello";
    }

}
