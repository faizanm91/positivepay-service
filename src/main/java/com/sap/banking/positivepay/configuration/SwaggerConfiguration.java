package com.sap.banking.positivepay.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@Import({ springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class,
        springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class SwaggerConfiguration {

    @Bean
    public Docket positivePaySwaggerApi() {
        return new Docket(SWAGGER_2)
                .groupName("OCB Extension API")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api/positivepays.*")).build();
    }

    /**
     * Build ApiInfo
     *
     * @return
     */
    private ApiInfo getApiInfo() {

        Contact contact = new Contact("Sachin Bhosale", "https://www.linkedin.com/in/sachinbhosale/", "s.bhosale@sap.com");

        VendorExtension<String> ext = new StringVendorExtension("SAP", "SAP Labs India (Pune)");

        return new ApiInfo("OCB PositivePay Extension API", "PositivePay API", "1.0.0-Draft", "", contact, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList(ext));

    }

}