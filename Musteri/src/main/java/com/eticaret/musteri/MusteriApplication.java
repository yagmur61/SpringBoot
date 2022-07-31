package com.eticaret.musteri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.eticaret.musteri", "com.eticaret.kutuphane"})
@EnableJpaRepositories(value = "com.eticaret.kutuphane.repository")
@EntityScan(value = "com.eticaret.kutuphane.model")
public class MusteriApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusteriApplication.class, args);
    }

}
