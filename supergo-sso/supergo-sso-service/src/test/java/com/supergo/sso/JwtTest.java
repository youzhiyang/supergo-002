package com.supergo.sso;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

/**
 * Jwt测试类
 */
public class JwtTest {

    @Test
    public void createJwt() {
        /**
         * base64加密
         */
        String token = Jwts.builder()
                .setId("123")         //用户id
                .setSubject("admin")  //用户名
                //设置签发时间
                .setIssuedAt(new Date())
                //签名
                .signWith(SignatureAlgorithm.HS256,"abc123")  //密码
                .compact();
        System.out.println(token);
    }

    /**
     * 解析jwt
     */
    @Test
    public void parseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MDQ3NDc2OCwiZXhwIjoxNTkwNDc0ODI4LCJhYWEiOjEsImJiYiI6Mn0.olSY5259WKiLZ4IRrzDFJgkODS5LNw7gIu647-DeL38";
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MDQ2NDAyNX0.eL4D5gg5pe9J3tAz8fUrSaBM1w4nNV1xu1H5bhUqGzM";
        Jws<Claims> jws = Jwts.parser().setSigningKey("abc123").parseClaimsJws(token);
        Claims claims = jws.getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.get("aaa"));
        System.out.println(claims.get("bbb"));
    }

    /**
     * 自定义属性
     */
    @Test
    public void createExpireToken() {
        long curTime = System.currentTimeMillis();
        long expTime = curTime + 60 * 1000;
        String token = Jwts.builder()
                .setId("123")
                .setSubject("admin")
                .setIssuedAt(new Date())
                .setExpiration(new Date(expTime))
                //自定义属性
                .claim("aaa",1)
                .claim("bbb",2)
                .signWith(SignatureAlgorithm.HS256, "abc123")
                .compact();

        System.out.println(token);
    }
}
