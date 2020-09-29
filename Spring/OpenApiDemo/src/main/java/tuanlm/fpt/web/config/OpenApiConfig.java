package tuanlm.fpt.web.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI configOpenAPI() {
		return new OpenAPI()
				.servers(Arrays.asList(new Server().url("http://localhost:8080")))
				.info(new Info().title("TuanLM's API")
						.description("Sample OpenAPI")
						.contact(new Contact()
								.email("tuanvipandpro@gmail.com")
								.name("TuanLM")
								.url("https://www.facebook.com/tuanlm110/"))
						.license(new License()
								.name("Apache 2.0")
								.url("http://www.apache.org/licenses/LICENSE-2.0.html"))
						.version("1.0"));
						
	}
}
