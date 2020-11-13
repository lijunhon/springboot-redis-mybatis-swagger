package org.springbootmybaitsredis.redis1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.springbootmybatisredis.redis1.mapper")
public class Redis1Application {

    public static void main(String[] args) {
        SpringApplication.run(Redis1Application.class, args);
    }

}
