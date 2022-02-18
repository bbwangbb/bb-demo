package cn.mb.webdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/3/24
 */
@RestController
@Api(tags = "TestController", description = "测试管理")
public class TestController {
    @ApiOperation("测试")
    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
