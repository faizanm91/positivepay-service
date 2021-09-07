package com.sap.banking.positivepay.controller.beans;

public class SecureUser {

    private String id;
    private String appType;
    private String channelType;
    private String entitlementID;
    private String servicePackageId;
    private String userName;
    private String bankID;
    private String affiliateID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getEntitlementID() {
        return entitlementID;
    }

    public void setEntitlementID(String entitlementID) {
        this.entitlementID = entitlementID;
    }

    public String getServicePackageId() {
        return servicePackageId;
    }

    public void setServicePackageId(String servicePackageId) {
        this.servicePackageId = servicePackageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getAffiliateID() {
        return affiliateID;
    }

    public void setAffiliateID(String affiliateID) {
        this.affiliateID = affiliateID;
    }
}
