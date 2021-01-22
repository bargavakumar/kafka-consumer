package com.prokarma.poc.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.prokarma.poc.consumer.dao.ConsumerDAOImpl;
import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();
    private ConsumerDAOImpl consumerDAOImpl;

    @Autowired
    ConsumerServiceImpl(ConsumerDAOImpl consumerDAOImpl) {
        this.consumerDAOImpl = consumerDAOImpl;
    }

    @PostConstruct
    void setDefaultValues() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void storeCustomerDetails(ObjectNode objectNode) {
        CustomerDetailsEntity customerDetails = objectMapper.convertValue(objectNode, CustomerDetailsEntity.class);
        customerDetails.setPayload(objectNode);
        Long id = consumerDAOImpl.storeCustomerDetails(customerDetails);
        logger.info("applicationName=Consumer|message=Customer details record inserted with {}:", id);
    }
}
