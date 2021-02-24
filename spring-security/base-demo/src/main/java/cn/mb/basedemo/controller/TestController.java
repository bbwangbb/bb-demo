package cn.mb.basedemo.controller;

import cn.mb.basedemo.domain.SecurityUser;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/index")
//    @Secured({"ROLE_role1", "ROLE_role2"})//    有角色才可以访问
//    @PreAuthorize("hasAnyAuthority('index12')")//   hasRole|hasAnyAuthority等几个方法，类似于配置类中设置
    public String index() {
        return "index";
    }

    @PostFilter("filterObject.id == 1")//   只将id==1的数据返回
    @GetMapping("/getAllUser")
    public List<SecurityUser> getAllUser() {
        List<SecurityUser> securityUserList = new ArrayList<>();
        securityUserList.add(new SecurityUser(1L, "1", "1"));
        securityUserList.add(new SecurityUser(2L, "2", "2"));
        return securityUserList;
    }

    @GetMapping("/authentication")
    public Authentication getUser(Authentication authentication){
        return authentication;
    }

    @GetMapping("/userDetail")
    public UserDetails getUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }


}
