package com.sl.nextflight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NextFlightApplication {

    public static void main(String[] args) {
        SpringApplication.run(NextFlightApplication.class, args);
    }

}
