package com.ll.nextjs20250110;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NextJs20250110Application {

    public static void main(String[] args) {
        SpringApplication.run(NextJs20250110Application.class, args);
    }

}
