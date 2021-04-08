package cn.edu.zucc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cn.edu.zucc.mapper")
public class GpApplication {
    public static void main(String[] args) {
        SpringApplication.run(GpApplication.class, args);
    }
}
