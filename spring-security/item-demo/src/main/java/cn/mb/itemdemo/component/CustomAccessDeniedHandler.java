package cn.mb.itemdemo.component;

import cn.hutool.json.JSONUtil;
import cn.mb.itemdemo.api.CommonResult;
import cn.mb.itemdemo.api.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  自定义无权访问处理器
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/2/24
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.toJsonStr(CommonResult.failed(ResultCode.FORBIDDEN)));
        response.getWriter().flush();
    }
}
