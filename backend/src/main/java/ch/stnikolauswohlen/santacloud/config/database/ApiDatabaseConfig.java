package ch.stnikolauswohlen.santacloud.config.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "api.database")
@Validated
@Getter
@Setter
public class ApiDatabaseConfig
{
    private String driverClassName;
    private String hibernateDialect;
    private String hibernateDdlAuto;
    private String username;
    private String password;
    private String url;
}
