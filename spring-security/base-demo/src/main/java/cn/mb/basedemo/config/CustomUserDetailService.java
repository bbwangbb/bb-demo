package cn.mb.basedemo.config;

import cn.mb.basedemo.dao.SecurityUserDao;
import cn.mb.basedemo.domain.SecurityUser;
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


/**
 * <p>
 *  自定义UserDetailsService
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/22
 */
@Service("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private SecurityUserDao securityUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //  假数据
//        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
//        return new User("root", new BCryptPasswordEncoder().encode("root"), authorityList);
        //  DB数据
        SecurityUser securityUser = securityUserDao.selectOne(Wrappers.lambdaQuery(SecurityUser.class)
                .eq(SecurityUser::getUsername, username)
        );
        if (securityUser != null) {
            /**
             * 权限
             *  ROLE_XXX：表示已有角色(基于角色的访问控制)
             *  非ROLE_：表示已有权限(基于资源的访问控制)
             */
            //  TODO 在这里带上权限
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("index");
            return new User(securityUser.getUsername(), new BCryptPasswordEncoder().encode(securityUser.getPassword()), authorityList);
        }
        //  不存在抛自带异常
        throw new UsernameNotFoundException("用户名不存在");
    }
}
