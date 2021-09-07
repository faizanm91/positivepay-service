package com.sap.banking.positivepay.model;

import com.sap.banking.positivepay.validation.ValidationGroups;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class PositivePay {

    @GeneratedValue
    @Id
    @NotNull(groups = {ValidationGroups.DeletePositivePay.class, ValidationGroups.ModifyPositivePay.class})
    private Long id;

    @NotNull(groups = {Default.class, ValidationGroups.AddPositivePay.class, ValidationGroups.ModifyPositivePay.class})
    private String accountNumber;

    @NotNull(groups = {Default.class, ValidationGroups.AddPositivePay.class, ValidationGroups.ModifyPositivePay.class})
    private String issuerName;

    @NotNull(groups = {Default.class, ValidationGroups.AddPositivePay.class, ValidationGroups.ModifyPositivePay.class})
    private String chequeNumber;

    @NotNull(groups = {Default.class, ValidationGroups.AddPositivePay.class, ValidationGroups.ModifyPositivePay.class})
    private String chequeBeneficiaryName;

    @NotNull(groups = {Default.class, ValidationGroups.AddPositivePay.class, ValidationGroups.ModifyPositivePay.class})
    private BigDecimal chequeAmount;

    @NotNull(groups = {Default.class, ValidationGroups.AddPositivePay.class, ValidationGroups.ModifyPositivePay.class})
    private Date chequeIssueDate;

    public Long id() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getChequeBeneficiaryName() {
        return chequeBeneficiaryName;
    }

    public void setChequeBeneficiaryName(String chequeBeneficiaryName) {
        this.chequeBeneficiaryName = chequeBeneficiaryName;
    }

    public BigDecimal getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(BigDecimal chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public Date getChequeIssueDate() {
        return chequeIssueDate;
    }

    public void setChequeIssueDate(Date chequeIssueDate) {
        this.chequeIssueDate = chequeIssueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositivePay)) return false;

        PositivePay that = (PositivePay) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getAccountNumber() != null ? !getAccountNumber().equals(that.getAccountNumber()) : that.getAccountNumber() != null)
            return false;
        if (getIssuerName() != null ? !getIssuerName().equals(that.getIssuerName()) : that.getIssuerName() != null)
            return false;
        if (getChequeNumber() != null ? !getChequeNumber().equals(that.getChequeNumber()) : that.getChequeNumber() != null)
            return false;
        if (getChequeBeneficiaryName() != null ? !getChequeBeneficiaryName().equals(that.getChequeBeneficiaryName()) : that.getChequeBeneficiaryName() != null)
            return false;
        if (getChequeAmount() != null ? !getChequeAmount().equals(that.getChequeAmount()) : that.getChequeAmount() != null)
            return false;
        return getChequeIssueDate() != null ? getChequeIssueDate().equals(that.getChequeIssueDate()) : that.getChequeIssueDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        result = 31 * result + (getIssuerName() != null ? getIssuerName().hashCode() : 0);
        result = 31 * result + (getChequeNumber() != null ? getChequeNumber().hashCode() : 0);
        result = 31 * result + (getChequeBeneficiaryName() != null ? getChequeBeneficiaryName().hashCode() : 0);
        result = 31 * result + (getChequeAmount() != null ? getChequeAmount().hashCode() : 0);
        result = 31 * result + (getChequeIssueDate() != null ? getChequeIssueDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PositivePay{" +
                "id='" + id + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", chequeNumber='" + chequeNumber + '\'' +
                ", chequeBeneficiaryName='" + chequeBeneficiaryName + '\'' +
                ", chequeAmount=" + chequeAmount +
                ", chequeIssueDate=" + chequeIssueDate +
                '}';
    }
}
