package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("controllers"))
                .paths(PathSelectors.ant("/v1/diff/{id}/*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Waes Application Challenge",
                "This is a simple challenge to show coding skills and what is most value in software engineering. This api consist in provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints, the provided data needs to be diff-ed and the results shall be available on a third endpoint.",
                "1.0",
                "Terms of service",
                new Contact("Leonardo Mendes", "www.github.com/leonardo-mendes/waesChallenge", "leonardocm92@hotmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}