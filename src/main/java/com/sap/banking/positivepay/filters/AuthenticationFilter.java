package com.sap.banking.positivepay.filters;
import com.sap.banking.positivepay.controller.beans.AuthenticationContext;
import com.sap.banking.positivepay.controller.beans.AuthenticationResponse;
import com.sap.banking.positivepay.service.BankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter{
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private static final String TOKEN_KEY_HEADER = "TokenKey";
    private static final String X_CSRF_TOKEN_HEADER = "x-csrf-token";

    private final BankingService bankingService;

    public AuthenticationFilter(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // if authenticated user request, continue processing else return Unauthorized response
        if(isAuthenticatedUserRequest((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean isAuthenticatedUserRequest(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Checking OCB User Authentication");
        String tokenKey = request.getHeader(TOKEN_KEY_HEADER);
        String csrfToken = request.getHeader(X_CSRF_TOKEN_HEADER);

        if(isEmpty(tokenKey) || isEmpty(csrfToken)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            logger.error("Unauthorized access:TokenKey is absent");
            return false;
        }
        logger.info("Trying to login with TokenKey : {}, CsrfToken : {}", tokenKey, csrfToken);
        try{
            AuthenticationResponse authenticationResponse = bankingService.getSecureUser(tokenKey,csrfToken);

            if(!HttpStatus.OK.equals(authenticationResponse.getStatus())){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                logger.error("Authentication failed with TokenKey");
                return false;
            }

            ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            AuthenticationContext authenticationContext = ctx.getBean(AuthenticationContext.class);
            authenticationContext.setAuthenticationToken(tokenKey);
            authenticationContext.setCsrfToken(csrfToken);
            logger.info("Authentication successful with TokenKey");
        }catch (Exception ex){
            logger.error("Error in calling OCB Authentication API", ex);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        return true;
    }

    private boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }
}
