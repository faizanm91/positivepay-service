package com.sap.banking.positivepay.events.impl;

import com.sap.banking.positivepay.events.KymaEventType;

public enum PositivePayEventType implements KymaEventType {
    PositivePayCreated("sap.kyma.custom.positivepayapp.positivepay.created.v1"),
    PositivePayDeleted("sap.kyma.custom.positivepayapp.positivepay.deleted.v1"),
    PositivePayModified("sap.kyma.custom.positivepayapp.positivepay.modified.v1");

    private final String type;

    private PositivePayEventType(String type1) {
        this.type = type1;
    }

    public String type() {
        return type;
    }
}