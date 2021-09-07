package com.sap.banking.positivepay.events.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.banking.positivepay.events.KymaEvent;
import com.sap.banking.positivepay.events.KymaEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Profile("kyma")
@Component
public class KymaEventPublisherImpl implements KymaEventPublisher {
    private static final Logger logger = LoggerFactory.getLogger(KymaEventPublisherImpl.class);
    private static final String CLOUDEVENTS_JSON = "application/cloudevents+json";

    @Value("${kyma.event.publisher.url}")
    private String kymaEventPublisherURL;

    private final RestTemplate restTemplate = kymaApiRestTemplate();

    @Override
    public void publishEvent(@NotNull KymaEvent<?> event) {
        RequestEntity<?> request = RequestEntity
                .post(kymaEventPublisherURL)
                .contentType(MediaType.parseMediaType(CLOUDEVENTS_JSON))
                .accept(MediaType.APPLICATION_JSON)
                .body(event);
        logger.info("Publishing event \n {}", event);

        // send request asynchrnously to Kyma Event Publisher
        CompletableFuture.supplyAsync(() -> restTemplate.exchange(request, Object.class))
                .thenAccept(res -> System.out.println("Kyma Event Publish Response Code : " + res.getStatusCode()));

        logger.info("Published KymaEvent {}", event);
    }

    private RestTemplate kymaApiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper mapper = new ObjectMapper();
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
