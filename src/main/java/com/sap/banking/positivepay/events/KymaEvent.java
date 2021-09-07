package com.sap.banking.positivepay.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.UUID;

public class KymaEvent<T> {
    private static final Logger logger = LoggerFactory.getLogger(KymaEvent.class);

    private String source = "kyma";
    private String specversion = "1.0";
    private String eventtypeversion = "v1";
    private String datacontenttype = MediaType.APPLICATION_JSON_VALUE;
    private String id = UUID.randomUUID().toString();
    // only type and data to be specified
    private String type;
    private T data;

    public KymaEvent(KymaEventType kymaEventType, T eventData1) {
        this.type = kymaEventType.type();
        this.data = eventData1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSpecversion() {
        return specversion;
    }

    public void setSpecversion(String specversion) {
        this.specversion = specversion;
    }

    public String getEventtypeversion() {
        return eventtypeversion;
    }

    public void setEventtypeversion(String eventtypeversion) {
        this.eventtypeversion = eventtypeversion;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDatacontenttype() {
        return datacontenttype;
    }

    public void setDatacontenttype(String datacontenttype) {
        this.datacontenttype = datacontenttype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "KymaEvent : " + toJson();
    }

    private String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.debug(e.getMessage());
        }
        return "{}";
    }
}
