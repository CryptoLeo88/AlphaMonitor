package com.block;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan("com.block.mapper")
@EnableOpenApi
public class BlockchainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainApplication.class, args);
    }



}
