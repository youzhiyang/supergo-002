package com.supergo.sso;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptTest {

    /**
     * 密码加密
     */
    @Test
    public void testBCrypt() {
        String passsword = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(passsword);
    }

    /**
     * 密码校验
     */
    @Test
    public void checkPassword() {
        String passsword = "$2a$10$Tb2x97n5TeZcux9m5/zoguL1iG0B00/XFk.CTxP2qZFMSo88VbyKe";
        //密码校验
        //1、明文密码   2、加密之后的结果
        boolean result = BCrypt.checkpw("123", passsword);
        System.out.println(result);
    }
}
