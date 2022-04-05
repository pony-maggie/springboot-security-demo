package com.lucas.token.tokendemo;


import com.lucas.token.tokendemo.domain.Menu;
import com.lucas.token.tokendemo.domain.User;
import com.lucas.token.tokendemo.mapper.MenuMapper;
import com.lucas.token.tokendemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testBCcrytPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String str1 = passwordEncoder.encode("1234");
        String str2 = passwordEncoder.encode("1234");
        System.out.println(str1);
        System.out.println(str2);
    }

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSelectPerms() {
        List<String> str = menuMapper.selectPermsByUserId(2L);
        System.out.println(str);
    }
}
