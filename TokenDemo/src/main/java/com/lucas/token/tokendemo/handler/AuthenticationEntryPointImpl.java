package com.lucas.token.tokendemo.handler;

import com.alibaba.fastjson.JSON;
import com.lucas.token.tokendemo.domain.ReponseResult;
import com.lucas.token.tokendemo.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//认证异常处理，统一返回json给到前端
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ReponseResult result = new ReponseResult(HttpStatus.UNAUTHORIZED.value(), "用户认证失败");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
