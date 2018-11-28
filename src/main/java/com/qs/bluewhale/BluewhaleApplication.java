package com.qs.bluewhale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
//@ComponentScan(basePackages = {
//        "com.qs.bluewhale.config",
//        "com.qs.bluewhale.controller",
//        "com.qs.bluewhale.mapper",
//        "com.qs.bluewhale.service"})
public class BluewhaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluewhaleApplication.class, args);
    }

}
