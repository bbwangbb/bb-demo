package cn.mb.itemdemo.controller;

import cn.mb.itemdemo.api.CommonResult;
import cn.mb.itemdemo.service.UserService;
import cn.mb.itemdemo.util.JwtTokenUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/24
 */
@RestController
public class TestController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public TestController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @GetMapping("/login")
    public CommonResult login() {
        UserDetails userDetails = userService.loadUserByUsername("root");
        String token = jwtTokenUtil.generateToken(userDetails);
        return CommonResult.success(token);
    }

    @GetMapping("/index")
    public CommonResult hello() {
        return CommonResult.success("index");
    }

    @GetMapping("/update")
    public CommonResult update() {
        return CommonResult.success("update");
    }

}
