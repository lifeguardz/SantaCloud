package ch.stnikolauswohlen.santacloud.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties("api")
@Validated
@Getter
@Setter
public class ApiConfig
{
    private String version;
    private String clientAuthToken;
}
