package com.sap.banking.positivepay.service.impl;

import com.sap.banking.positivepay.controller.beans.AccountListResponse;
import com.sap.banking.positivepay.controller.beans.AuthenticationResponse;
import com.sap.banking.positivepay.service.BankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankingServiceImpl implements BankingService {
    private static final Logger logger = LoggerFactory.getLogger(BankingServiceImpl.class);
    private static final String TOKEN_KEY_HEADER = "TokenKey";
    private static final String X_CSRF_TOKEN_HEADER = "x-csrf-token";

    @Value("${ocb.authentication.service.url}")
    private String OCB_AUTHENTICATION_URI;

    @Value("${ocb.accounts.service.url}")
    private String OCB_GET_ACCOUNTS_URI;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AuthenticationResponse getSecureUser(String tokenKey, String csrfToken){
        HttpHeaders httpHeaders = getHttpHeaders(tokenKey, csrfToken);
        HttpEntity<?> requestEntity = new HttpEntity(httpHeaders);

        ResponseEntity<AuthenticationResponse> authenticationResponse = restTemplate.exchange(OCB_AUTHENTICATION_URI + "/SecureUsers", HttpMethod.GET, requestEntity, AuthenticationResponse.class);
        authenticationResponse.getBody().setStatus(authenticationResponse.getStatusCode());

        return authenticationResponse.getBody();
    }

    @Override
    public AccountListResponse getAccounts(String tokenKey, String csrfToken){
        HttpHeaders httpHeaders = getHttpHeaders(tokenKey, csrfToken);
        HttpEntity<?> requestEntity = new HttpEntity(httpHeaders);

        ResponseEntity<AccountListResponse> accountListResponseResponse = restTemplate.exchange(OCB_GET_ACCOUNTS_URI + "/Accounts", HttpMethod.GET, requestEntity, AccountListResponse.class);
        accountListResponseResponse.getBody().setStatus(accountListResponseResponse.getStatusCode());
        logger.info("GetAccounts Response : {} and count : {}",  accountListResponseResponse.getStatusCode(), accountListResponseResponse.getBody().count());

        return accountListResponseResponse.getBody();
    }

    private HttpHeaders getHttpHeaders(String tokenKey, String csrfToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(TOKEN_KEY_HEADER, tokenKey);
        httpHeaders.add(X_CSRF_TOKEN_HEADER, csrfToken);
        return httpHeaders;
    }
}
