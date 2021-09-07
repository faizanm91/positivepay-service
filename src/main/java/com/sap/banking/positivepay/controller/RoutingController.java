package com.sap.banking.positivepay.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * RoutingController to route requests to API documentation
 *
 * @author I313873
 *
 */
@Controller
public class RoutingController {

    @GetMapping("/")
    public ResponseEntity<?> routeToDocumentation(UriComponentsBuilder uriBuilder) {
        UriComponents apiDocumentationURI = uriBuilder.path("/swagger-ui/index.html").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(apiDocumentationURI.toUri());

        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }
}
