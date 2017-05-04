package uk.doh.oht.database.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ConditionalOnProperty(prefix = "swagger.enable", name = "dynamic", matchIfMissing = true)
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.all:false}")
    boolean swaggerShowAll;

    @Bean
    public Docket api() {
        final java.util.function.Predicate<RequestHandler> apisPredicate = apisPredicate();
        final java.util.function.Predicate<String> pathsPredicate = PathSelectors.any()::apply;
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(apisPredicate::test)
                .paths(pathsPredicate::test)
                .build();
    }

    private java.util.function.Predicate<RequestHandler> apisPredicate() {
        if (swaggerShowAll) {
            return RequestHandlerSelectors.any()::apply;
        }
        return RequestHandlerSelectors.basePackage("uk.doh.oht")::apply;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OHT Database Service")
                .description("OHT Datbase service")
                .termsOfServiceUrl("*REFERENCE TERMS OF SERVICE HERE*")
                .license("*NAME LICENSE HERE*")
                .licenseUrl("*REFERENCE LICENSE HERE*")
                .version("0.0-ALPHA")
                .build();
    }
}