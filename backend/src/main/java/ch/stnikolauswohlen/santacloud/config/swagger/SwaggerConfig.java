package ch.stnikolauswohlen.santacloud.config.swagger;

import ch.stnikolauswohlen.santacloud.config.ApiConfig;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer
{
    private final ApiConfig apiConfig;

    @Autowired
    public SwaggerConfig(final ApiConfig apiConfig)
    {
        this.apiConfig = apiConfig;
    }

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("ch.stnikolauswohlen.santacloud"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(createApiInfo());
    }

    private ApiInfo createApiInfo()
    {
        return new ApiInfo(
            "SantaCloud",
            "SantaCloud Interfaces",
            "v1: " + apiConfig.getVersion(),
            "",
            new Contact("St. Nikolaus Wohlen", "https://stnikolaus-wohlen.ch/", "st.nikolauswohlen@bluewin.ch"),
            "",
            "",
            new ArrayList<>()
        );
    }
}
