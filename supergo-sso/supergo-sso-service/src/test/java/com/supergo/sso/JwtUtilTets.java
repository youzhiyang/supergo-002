package com.supergo.sso;

import com.supergo.sso.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsoApplication.class)
public class JwtUtilTets {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void createJwt() {
        String token = jwtUtil.createToken("16", "user_4", "admin");
        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNiIsInN1YiI6InVzZXJfNCIsImlhdCI6MTU5MjEyNTgxOSwicm9sZSI6ImFkbWluIiwiZXhwIjoxNTkyMTI5NDE5fQ.0aexrZL6Ve5upbEiZMebxb2xGUKvi2n2O0-xxs4LJ78";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims.toString());
    }
}
