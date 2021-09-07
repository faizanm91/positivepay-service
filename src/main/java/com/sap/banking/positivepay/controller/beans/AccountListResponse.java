package com.sap.banking.positivepay.controller.beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountListResponse {
    @JsonAlias({"d"})
    private InternalAccountResponse d;
    private HttpStatus status;

    public InternalAccountResponse getD() {
        return d;
    }

    public void setD(InternalAccountResponse d) {
        this.d = d;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public class InternalAccountResponse{
        @JsonAlias({"results"})
        private List<Account> results = new ArrayList<>();

        public List<Account> getResults() {
            return results;
        }

        public void setResults(List<Account> results1) {
            this.results.addAll(results1);
        }
    }

    public List<Account> getAccounts(){
        return d != null ? d.results : Collections.emptyList();
    }

    public int count(){
        return d == null ? 0 : d.results.size();
    }
}
