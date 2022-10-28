package com.suninjjang.oasis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OasisApplication{
    public static void main(String[] args) {
        SpringApplication.run(OasisApplication.class, args);
    }

}