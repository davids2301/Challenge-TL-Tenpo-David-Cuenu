package cl.com.tenpo.domain.configuration.client;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public Retryer retryer() {
        return new CustomRetryer();
    }

}
