package com.sap.banking.positivepay.persistence;

import com.sap.banking.positivepay.model.PositivePay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositivePayRepository extends JpaRepository<PositivePay, Long> {
}
