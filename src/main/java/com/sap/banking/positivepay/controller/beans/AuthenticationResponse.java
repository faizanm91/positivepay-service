package com.sap.banking.positivepay.controller.beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.http.HttpStatus;

import java.util.List;

public class AuthenticationResponse {
    private HttpStatus status;
    @JsonAlias({"d"})
    private InternalResponse d;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public class InternalResponse{
        @JsonAlias({"results"})
        private List<SecureUser> results;

        public List<SecureUser> getResults() {
            return results;
        }

        public void setResults(List<SecureUser> results) {
            this.results = results;
        }
    }

    public InternalResponse getD() {
        return d;
    }

    public void setD(InternalResponse d) {
        this.d = d;
    }
}
