package cn.edu.zucc.interceptor;

import cn.edu.zucc.exception.UnauthorizedException;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.utils.TokenProviderUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        if (token != null) {

            boolean result = TokenProviderUtils.verify(token, tokenSecret);
            if (result) {
                log.debug("passed login interceptor");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().append(new ObjectMapper().writeValueAsString(ResponseBuilder.buildErrorResponse(new UnauthorizedException())));
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }

        return false;
    }
}
