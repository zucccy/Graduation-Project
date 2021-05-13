package cn.edu.zucc.config;

import cn.edu.zucc.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @since 05-10-2021
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;

    public WebMvcConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(3000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/user/login");
        excludePath.add("/user/register");
        excludePath.add("/hospital/**");
        excludePath.add("/office/**");
        excludePath.add("/doctor/**");
        excludePath.add("/static/**");
        excludePath.add("/assets/**");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/swagger-ui.html/**");
        excludePath.add("/v2/**");
        excludePath.add("/webjars/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}