package cn.mb.itemdemo.service;

import cn.mb.itemdemo.dao.SecurityUserDao;
import cn.mb.itemdemo.domain.SecurityUser;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private SecurityUserDao securityUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = securityUserDao.selectOne(Wrappers.lambdaQuery(SecurityUser.class)
                .eq(SecurityUser::getUsername, username)
        );
        if (securityUser != null) {
            //  用户权限
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("/update");
            return new User(securityUser.getUsername(), new BCryptPasswordEncoder().encode(securityUser.getPassword()), authorityList);
        }
        //  不存在抛自带异常
        throw new UsernameNotFoundException("用户名不存在");
    }
}
