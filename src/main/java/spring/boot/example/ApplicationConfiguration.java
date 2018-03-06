package spring.boot.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @author uchonyy@gmail.com
 */
@SpringBootApplication
public class ApplicationConfiguration {

    /**
     * Запуск Spring Boot
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }

}