package com.lucas.token.tokendemo.service.impl;

import com.lucas.token.tokendemo.domain.LoginUser;
import com.lucas.token.tokendemo.domain.ReponseResult;
import com.lucas.token.tokendemo.domain.User;
import com.lucas.token.tokendemo.service.LoginService;
import com.lucas.token.tokendemo.utils.JwtUtil;
import com.lucas.token.tokendemo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;


    @Override
    public ReponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication == null) {
            throw new RuntimeException("登录失败");
        }

        //认证通过了，使用userid生成jwt
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        redisCache.setCahcheObject("login:" + userId, loginUser);
        return new ReponseResult(200,"登录成功", map);
    }
}
