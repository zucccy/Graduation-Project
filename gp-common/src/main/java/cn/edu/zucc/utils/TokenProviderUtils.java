package cn.edu.zucc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 13.04.2021
 */
@Slf4j
public class TokenProviderUtils {
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000L; // 有效期24小时

    private TokenProviderUtils() {
    }

    /**
     * 签发
     *
     * @param userId 登录信息
     * @return token
     */
    public static String sign(Long userId, String tokenSecret) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withClaim("userId", userId)
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(tokenSecret));
        } catch (IllegalArgumentException | JWTCreationException e) {
            log.error(e.getMessage(), e);
        }
        return token;
    }


    /**
     * 校验
     *
     * @param token       token
     * @param tokenSecret 密钥
     * @return boolean
     */
    public static boolean verify(String token, String tokenSecret) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(tokenSecret)).build();
            DecodedJWT jwt = verifier.verify(token);
            log.debug("verify access" + jwt.getClaim("userId").asLong());
        } catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
