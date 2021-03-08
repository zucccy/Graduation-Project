package cn.edu.zucc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description: Swagger配置类
 *
 * @author chenyun
 * @since 09.03.2021
 */
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig implements ApplicationListener<WebServerInitializedEvent> {

    public static final String API_DIRECTORY = "cn.edu.zucc.controller";

    @Bean
    public Docket docket() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("毕业设计")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true) // swagger开关
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage(API_DIRECTORY)) // swagger扫描路径
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void onApplicationEvent(@NonNull WebServerInitializedEvent webServerInitializedEvent) {

        int port = webServerInitializedEvent.getWebServer().getPort();
        String applicationName = webServerInitializedEvent.getApplicationContext().getApplicationName();
        try {
            log.info("swagger-ui已开启");
            log.info("http://" + InetAddress.getLocalHost().getHostAddress() + ":" + port + applicationName + "/swagger-ui.html");
        } catch (UnknownHostException e) {
            log.error("error for finding swagger address!", e);
        }
    }
}

