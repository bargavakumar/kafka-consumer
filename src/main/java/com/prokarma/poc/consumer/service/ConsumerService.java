package com.prokarma.poc.consumer.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface ConsumerService {

    void storeCustomerDetails(JsonNode jsonNode);
    void createErrorRecord(Throwable jsonNode);
}
