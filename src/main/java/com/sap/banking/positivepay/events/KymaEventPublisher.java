package com.sap.banking.positivepay.events;

public interface KymaEventPublisher {

    /**
     * Publish KymaEvent with any data load
     *
     * @param kymaEvent
     */
    void  publishEvent(KymaEvent<?> kymaEvent);
}
