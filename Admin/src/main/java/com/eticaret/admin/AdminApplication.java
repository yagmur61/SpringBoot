package com.eticaret.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages =  {"com.eticaret.kutuphane.*", "com.eticaret.admin.*"})
@EnableJpaRepositories(value = "com.eticaret.kutuphane.repository")
@EntityScan(value = "com.eticaret.kutuphane.model")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
