package fr.ul.mygameslibapirest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SpringDocUtils.getConfig().replaceWithClass(org.springframework.data.domain.Pageable.class, org.springdoc.core.converters.models.Pageable.class);

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("My Games Lib")
                        .description("Swagger-UI Back-End")
                        .version("v1")
                );
    }
}
