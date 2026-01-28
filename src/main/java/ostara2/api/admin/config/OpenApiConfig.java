package ostara2.api.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ostaraOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ostara Admin API")
                        .description("Backend API for Ostara system")
                        .version("2.0.1")
                        .contact(new Contact()
                                .name("Luka Maček")
                                .email("luka.macek@siol.net")));
    }
}
