package com.lucas.token.tokendemo.service;

import com.lucas.token.tokendemo.domain.ReponseResult;
import com.lucas.token.tokendemo.domain.User;

public interface LoginService {
    public ReponseResult login(User user);

}
