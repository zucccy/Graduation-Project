package cn.edu.zucc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 13.04.2021
 */
@Slf4j
public class TokenUtils {
    private TokenUtils() {
    }

    public static DecodedJWT getJwt(String token, String tokenSecret) {
        return JWT.require(Algorithm.HMAC256(tokenSecret)).build().verify(token);
    }

    public static Long getUserId(String token, String tokenSecret) {
        return getJwt(token, tokenSecret).getClaim("userId").asLong();
    }
}
