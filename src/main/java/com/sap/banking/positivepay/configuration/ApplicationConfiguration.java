package com.sap.banking.positivepay.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sap.banking.positivepay.controller.beans.AuthenticationContext;
import com.sap.banking.positivepay.filters.AuthenticationFilter;
import com.sap.banking.positivepay.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private BankingService bankingService;

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean  filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthenticationFilter(bankingService));
        filterRegistrationBean.addUrlPatterns("/api/*");

        return filterRegistrationBean;
    }

    @Bean
    @Scope(scopeName= WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.DEFAULT)
    public AuthenticationContext authContextBean() {
        AuthenticationContext authContext = new AuthenticationContext();
        return authContext;
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(mapper);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(jsonMessageConverter);

        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

}
