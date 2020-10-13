package com.fhict.pacmenrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fhict.pacmenrest")
public class PacMenRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacMenRestApplication.class, args);
    }

}
