package com.sap.banking.positivepay.service;

import com.sap.banking.positivepay.model.PositivePay;

import java.util.Collection;
import java.util.Optional;

public interface PositivePayService {
    Collection<PositivePay> getAllPositivePays();

    void deletePositivePayRequest(Long applicationId);

    PositivePay savePositivePayRequest(PositivePay application);

    PositivePay updatePositivePayRequest(PositivePay positivePay, Long id);

    Optional<PositivePay> getPositivePayById(Long applicationId);
}
