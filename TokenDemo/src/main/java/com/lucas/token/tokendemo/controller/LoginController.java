package com.lucas.token.tokendemo.controller;

import com.lucas.token.tokendemo.domain.ReponseResult;
import com.lucas.token.tokendemo.domain.User;
import com.lucas.token.tokendemo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/user/login")
    public ReponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }
}
