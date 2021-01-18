package com.prokarma.poc.consumer.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ConsumerService {

    void storeCustomerDetails(ObjectNode objectNode);
}
