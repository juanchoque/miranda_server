package com.codeformas.miranda_server.swaggerConfig;

import com.codeformas.miranda_server.util.ConstantMiranda;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public Docket recipeApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(ConstantMiranda.BASE_PACKAGE))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){

        ApiInfo apiInfo = new ApiInfo(
                ConstantMiranda.TITLE_API,
                ConstantMiranda.DESCRIPTION_API,
                ConstantMiranda.VERSION_API,
                ConstantMiranda.TERM_SERVICE_URL_API,
                ConstantMiranda.CONTACT_API,
                ConstantMiranda.LICENCE_API,
                ConstantMiranda.URL_LICENCE_API
        );

        return apiInfo;
    }
}
