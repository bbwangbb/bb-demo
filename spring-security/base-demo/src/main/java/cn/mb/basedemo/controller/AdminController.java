package cn.mb.basedemo.controller;

import cn.mb.basedemo.config.CustomUserDetailService;
import cn.mb.basedemo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CustomUserDetailService userDetailService;

    @GetMapping("/test")
    public String test() {
        return "admin test";
    }

    @GetMapping("/login")
    public String login() {
        UserDetails userDetails = userDetailService.loadUserByUsername("root");
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
}
