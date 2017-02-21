package co.jtoehen.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jtoehen on 21/2/2017.
 */
@Configuration
public class SpringBootConfiguration
{
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
