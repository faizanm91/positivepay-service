package com.sap.banking.positivepay.service.impl;

import com.sap.banking.positivepay.exception.PositivePayNotFoundException;
import com.sap.banking.positivepay.model.PositivePay;
import com.sap.banking.positivepay.persistence.PositivePayRepository;
import com.sap.banking.positivepay.service.PositivePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositivePayServiceImpl implements PositivePayService {

    @Autowired
    private PositivePayRepository positivePayRepository;

    @Override
    public List<PositivePay> getAllPositivePays() {
        return positivePayRepository.findAll();
    }

    @Override
    public void deletePositivePayRequest(Long applicationId) {
        try {
            positivePayRepository.deleteById(applicationId);
        } catch (EmptyResultDataAccessException dataAccessException) {
            throw new PositivePayNotFoundException(String.valueOf(applicationId), dataAccessException);
        }
    }

    @Override
    public PositivePay savePositivePayRequest(PositivePay positivePay) {
        return positivePayRepository.save(positivePay);
    }

    @Override
    public PositivePay updatePositivePayRequest(PositivePay positivePay, Long id){

        try {
            PositivePay ppay = positivePayRepository.findById(id).get();
            if (ppay != null) {
                if (positivePay.getAccountNumber() != null) ppay.setAccountNumber(positivePay.getAccountNumber());
                if (positivePay.getIssuerName() != null) ppay.setIssuerName(positivePay.getIssuerName());
                if (positivePay.getChequeNumber() != null) ppay.setChequeNumber(positivePay.getChequeNumber());
                if (positivePay.getChequeBeneficiaryName() != null)
                    ppay.setChequeBeneficiaryName(positivePay.getChequeBeneficiaryName());
                if (positivePay.getChequeAmount() != null) ppay.setChequeAmount(positivePay.getChequeAmount());
                if (positivePay.getChequeIssueDate() != null) ppay.setChequeIssueDate(positivePay.getChequeIssueDate());
            } else {
                //ppay = positivePay;
                throw new PositivePayNotFoundException(String.valueOf(id));
            }
            positivePayRepository.save(ppay);
            return ppay;
        }catch (Exception dataAccessException){
            throw new PositivePayNotFoundException(String.valueOf(id), dataAccessException);
        }
    }

    @Override
    public Optional<PositivePay> getPositivePayById(Long positivePayId) {
        return positivePayRepository.findById(positivePayId);
    }

}
