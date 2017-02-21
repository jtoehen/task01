package co.jtoehen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

/**
 * Basic Spring Boot application.
 */
@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringbootApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
