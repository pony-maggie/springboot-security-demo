package com.lucas.token.tokendemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.management.loading.PrivateClassLoader;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    //有效期
    private static final Long JWT_TTL = 60 * 60 * 1000L; //一个小时
    //密钥
    private static final String JWT_KEY = "lucas";

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    public static String createJWT(String subject, Long ttl) {
        JwtBuilder builder = getJwtBuilder(subject, ttl, getUUID());
        return builder.compact();
    }

    public static String createJWT(String id, String subject, Long ttl) {
        JwtBuilder builder = getJwtBuilder(subject, ttl, id);
        return builder.compact();
    }

    public static JwtBuilder getJwtBuilder(String subject, Long ttl, String uuid) {
        /**
         * hs256指的就是hmacSHA256
         * https://blog.csdn.net/sdnyqfyqf/article/details/105534376
         */
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);

        if (ttl == null) {
            ttl = JWT_TTL;
        }

        long expMills = nowMills + ttl;
        Date expireDate = new Date(expMills);

        /**
         * https://blog.csdn.net/qq_37636695/article/details/79265711
         */
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("lucas")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expireDate);
    }

    /**
     * 生成加密后的密钥
     * @return
     * http://cn.voidcc.com/question/p-otczwpqx-ot.html
     * https://blog.csdn.net/yanbin0830/article/details/89929045
     */
    public static SecretKey generalKey() {
        byte[] encoded = Base64.getDecoder().decode(JWT_KEY);
        //密钥本身也包含了加密算法
        SecretKey key = new SecretKeySpec(encoded, 0, encoded.length, "AES");
        return key;
    }

    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception{
        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)         //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
        return claims;
    }

    public static void main(String[] args) throws Exception {
        JwtUtil util=   new JwtUtil();
        //eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTE0NiwiaWF0IjoxNTE3ODM1MDg2LCJqdGkiOiJqd3QifQ.ncVrqdXeiCfrB9v6BulDRWUDDdROB7f-_Hg5N0po980
        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTEwOSwiaWF0IjoxNTE3ODM1MDQ5LCJqdGkiOiJqd3QifQ.G_ovXAVTlB4WcyD693VxRRjOxa4W5Z-fklOp_iHj3Fg";
        Claims c=util.parseJWT(jwt);//注意：如果jwt已经过期了，这里会抛出jwt过期异常。
        System.out.println(c.getId());//jwt
        System.out.println(c.getIssuedAt());//Mon Feb 05 20:50:49 CST 2018
        System.out.println(c.getSubject());//{id:100,name:xiaohong}
        System.out.println(c.getIssuer());//null
        System.out.println(c.get("uid", String.class));//DSSFAWDWADAS...
    }


}
