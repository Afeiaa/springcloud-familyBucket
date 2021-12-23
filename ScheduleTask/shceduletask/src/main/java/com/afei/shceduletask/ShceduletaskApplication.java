package com.afei.shceduletask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ShceduletaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShceduletaskApplication.class, args);
    }

}
