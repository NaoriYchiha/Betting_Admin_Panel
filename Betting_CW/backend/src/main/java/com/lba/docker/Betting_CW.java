package com.lba.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.lba.docker")
@EnableCaching
public class Betting_CW {


    public static void main(String[] args) {
        SpringApplication.run(Betting_CW.class, args);
    }

}
