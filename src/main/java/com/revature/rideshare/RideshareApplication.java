package com.revature.rideshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RideshareApplication {
    public static void main(String[] args) {
        SpringApplication.run(RideshareApplication.class, args);
    }
}
