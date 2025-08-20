package com.block.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${web3j.client-address}")
    private String clientAddress;


    @Bean
    public Admin web3j() {
        return Admin.build(new HttpService(clientAddress));
    }
}
