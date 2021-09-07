package com.sap.banking.positivepay.controller.beans;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private String id;
    private String number;
    private String routingNum;
    private String bankName;
    private String type;
    private String accountGroup;
    private String status;
    private String currencyCode;
    private BigDecimal currentBalance;
    private BigDecimal availableBalance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoutingNum() {
        return routingNum;
    }

    public void setRoutingNum(String routingNum) {
        this.routingNum = routingNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", routingNum='" + routingNum + '\'' +
                ", bankName='" + bankName + '\'' +
                ", type='" + type + '\'' +
                ", accountGroup='" + accountGroup + '\'' +
                ", status='" + status + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", currentBalance=" + currentBalance +
                ", availableBalance=" + availableBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(number, account.number) && Objects.equals(routingNum, account.routingNum) && Objects.equals(bankName, account.bankName) && Objects.equals(type, account.type) && Objects.equals(accountGroup, account.accountGroup) && Objects.equals(status, account.status) && Objects.equals(currencyCode, account.currencyCode) && Objects.equals(currentBalance, account.currentBalance) && Objects.equals(availableBalance, account.availableBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, routingNum, bankName, type, accountGroup, status, currencyCode, currentBalance, availableBalance);
    }
}
