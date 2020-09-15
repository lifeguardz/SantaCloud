package ch.stnikolauswohlen.santacloud.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossOriginSecurityConfig implements WebMvcConfigurer
{
    @Override
    public void addCorsMappings(final CorsRegistry registry)
    {
        registry.addMapping("/v1/**")
            .allowedOrigins("*")
            .allowedMethods("HEAD", "OPTIONS", "POST", "GET", "PUT", "DELETE")
            .allowedHeaders(
                HttpHeaders.ACCEPT,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ORIGIN,
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD
            )
            .exposedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
            .allowCredentials(true)
            .exposedHeaders(HttpHeaders.CONTENT_RANGE, "Page-Total-Count");
    }
}
