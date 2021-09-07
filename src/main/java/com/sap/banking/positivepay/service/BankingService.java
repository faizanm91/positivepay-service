package com.sap.banking.positivepay.service;

import com.sap.banking.positivepay.controller.beans.AccountListResponse;
import com.sap.banking.positivepay.controller.beans.AuthenticationResponse;

public interface BankingService {
    AuthenticationResponse getSecureUser(String tokenKey, String csrfToken);

    AccountListResponse getAccounts(String tokenKey, String csrfToken);
}
