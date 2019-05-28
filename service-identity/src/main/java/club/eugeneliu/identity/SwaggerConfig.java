package club.eugeneliu.identity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("club.eugeneliu.identity.api"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("P2P借贷平台身份验证模块")
                .description("EEugeneSoft，目前人数为7人，注册资金500万")
                .termsOfServiceUrl("http://www.tfjybj.com")
                .version("1.0.0")
                .contact(new Contact("EugeneLiu", null, null))
                .license("刘一辰许可证")
                .licenseUrl("http://www.dmsdbj.com")
                .build();
    }
}
