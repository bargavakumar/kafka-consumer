package com.prokarma.poc.consumer.dao;

import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import com.prokarma.poc.consumer.entitties.ErrorLogEntity;

public interface ConsumerDAO {

    // Stores customer details
    Long storeCustomerDetails(CustomerDetailsEntity customerDetailsEntity);

    // Stores Error log details
    Long storeErrorLog(ErrorLogEntity errorLogEntity);
}
