package com.sap.banking.positivepay.controller;

import com.sap.banking.positivepay.controller.beans.AccountListResponse;
import com.sap.banking.positivepay.controller.beans.AuthenticationContext;
import com.sap.banking.positivepay.events.KymaEvent;
import com.sap.banking.positivepay.events.KymaEventPublisher;
import com.sap.banking.positivepay.events.impl.PositivePayEventType;
import com.sap.banking.positivepay.exception.PositivePayNotFoundException;
import com.sap.banking.positivepay.model.PositivePay;
import com.sap.banking.positivepay.service.BankingService;
import com.sap.banking.positivepay.service.PositivePayService;
import com.sap.banking.positivepay.validation.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class PositivePayController {

    @Autowired
    private PositivePayService positivePayService;

    @Autowired
    private BankingService bankingService;

    // only required in Kyma environment
    @Autowired(required = false)
    private KymaEventPublisher eventPublisher;

    /**
     * Get All Positive Pays
     *
     * @return
     */
    @GetMapping("/positivepays")
    @ResponseBody
    public Collection<PositivePay> listPositivePays() {

        return positivePayService.getAllPositivePays();
    }


    /**
     * Get resource uses ETag to save network bandwidth by returning Not_Modified(304) if resource is not changed
     *
     * @param positivePayId
     * @param request
     * @return
     */
    @GetMapping("/positivepays/{positivePayId}")
    @ResponseBody
    public ResponseEntity<PositivePay> getPositivePayById(@PathVariable @NotNull Long positivePayId, WebRequest request) {

        Optional<PositivePay> positivePayResult = positivePayService.getPositivePayById(positivePayId);

        if (!positivePayResult.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // consider hashCode as ETag value
        String etagValue = String.valueOf(positivePayResult.hashCode());

        // Check ETag with If-None-Match header sent by client and send NOT_MODIFIED(304) status code without content
        if (request.checkNotModified(etagValue)) {
            return new ResponseEntity<PositivePay>(HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok().eTag(etagValue).body(positivePayResult.get());
    }


    /**
     * Post returns URL of newly created resource in Location header and entity in body.
     *
     * @param positivePay
     * @param uriBuilder
     * @return
     */
    @PostMapping(value = "/positivepays", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addPositivePay(@RequestBody @Validated(ValidationGroups.AddPositivePay.class) PositivePay positivePay,
                                            UriComponentsBuilder uriBuilder,  WebRequest request) {
        PositivePay savedPositivePay = positivePayService.savePositivePayRequest(positivePay);
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        ServletRequest servletRequest = servletWebRequest.getNativeRequest(ServletRequest.class);
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletRequest.getServletContext());

        AuthenticationContext authenticationContext = ctx.getBean(AuthenticationContext.class);
        AccountListResponse  accounts = bankingService.getAccounts(authenticationContext.getAuthenticationToken(), authenticationContext.getCsrfToken());

        // Create PositivePayRequest and return the location URL to retrieve application with id.
        UriComponents uriComponents = uriBuilder.path("/positivepays/{positivePayId}").buildAndExpand(savedPositivePay.id());

        // publish if eventPublisher is available in Kyma Environment
        if(eventPublisher !=null){
            KymaEvent<PositivePay> positivePayEvent = new KymaEvent<>(PositivePayEventType.PositivePayCreated, savedPositivePay);
            eventPublisher.publishEvent(positivePayEvent);
        }

        // response should contain location URL to retrieve created resource
        return ResponseEntity.created(uriComponents.toUri()).body(positivePay);
    }


    /**
     * Put returns URL of newly created resource in Location header and entity in body.
     *
     * @param positivePay
     * @param uriBuilder
     * @return
     */
    @PutMapping(value = "/positivepays/{positivePayId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updatePositivePay(@RequestBody @Validated(ValidationGroups.AddPositivePay.class) PositivePay positivePay,
                                               @PathVariable @NotNull Long positivePayId, UriComponentsBuilder uriBuilder,  WebRequest request) {

        PositivePay savedPositivePay = null;
        try{
            savedPositivePay = positivePayService.updatePositivePayRequest(positivePay, positivePayId);
        }catch(PositivePayNotFoundException ppnfe){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Positive pay with this id is not found");
        }

        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        ServletRequest servletRequest = servletWebRequest.getNativeRequest(ServletRequest.class);
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletRequest.getServletContext());

        AuthenticationContext authenticationContext = ctx.getBean(AuthenticationContext.class);
        // Create PositivePayRequest and return the location URL to retrieve application with id.
        UriComponents uriComponents = uriBuilder.path("/positivepays/{positivePayId}").buildAndExpand(savedPositivePay.id());

        // response should contain location URL to retrieve created resource
        return ResponseEntity.ok(savedPositivePay);
    }

    /**
     * Delete returns No_Content(204) on successful delete and 404 if resource not found
     *
     * @param positivePayId
     */
    @DeleteMapping("/positivepays/{positivePayId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePositivePayRequest(@PathVariable @NotNull Long positivePayId) {
        positivePayService.deletePositivePayRequest(positivePayId);

        // publish if eventPublisher is available in Kyma Environment
        if (eventPublisher != null) {
            PositivePay deletedPP = new PositivePay();
            deletedPP.setId(positivePayId);

            KymaEvent<PositivePay> positivePayEvent = new KymaEvent<>(PositivePayEventType.PositivePayDeleted, deletedPP);
            eventPublisher.publishEvent(positivePayEvent);
        }
    }


}
