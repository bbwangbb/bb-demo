package cn.mb.itemdemo.component;

import cn.mb.itemdemo.service.UserService;
import cn.mb.itemdemo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  token解析过滤器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/24
 */
@Component
public class CustomTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //  获取请求头
        String header = request.getHeader(jwtTokenUtil.getTokenHeader());
        //  解析token
        if (header != null && header.startsWith(jwtTokenUtil.getTokenHead())) {
            String token = header.substring(jwtTokenUtil.getTokenHead().length());
            //  获取用户名
            String username = jwtTokenUtil.getUserNameFromToken(token);
            //  每次都重新查询用户及其权限(保证动态权限)
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(token, userDetails)) {
                //  将用户信息放入SecurityContextHolder中
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }


}
