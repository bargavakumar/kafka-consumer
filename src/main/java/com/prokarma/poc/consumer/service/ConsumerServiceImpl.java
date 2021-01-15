package com.prokarma.poc.consumer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.poc.consumer.dao.ConsumerDAO;
import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import com.prokarma.poc.consumer.entitties.ErrorLogEntity;
import com.prokarma.poc.consumer.repository.ErrorLogRepository;
import com.prokarma.poc.consumer.util.ConsumerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    ErrorLogRepository errorLogRepository;

    @Autowired
    ConsumerUtil consumerUtil;

    @Autowired
    ConsumerDAO consumerDAO;

    @Override
    @Transactional
    public void storeCustomerDetails(JsonNode jsonNode) {
        CustomerDetailsEntity customerDetails = objectMapper.convertValue(jsonNode, CustomerDetailsEntity.class);
        customerDetails.setPayload(jsonNode);
        logger.info("customer details object is {}", customerDetails);
        consumerDAO.storeCustomerDetails(customerDetails);

          /*  CustomerDetailsEntity customerDetailsEntity = consumerRepository.save(customerDetails);
            logger.info("Customer details record inserted with {}", customerDetailsEntity.getId());*/

    }

    @Override
    @Transactional
    public void createErrorRecord(Throwable ex) {
        try {
            ErrorLogEntity errorLogEntity = errorLogRepository.save(consumerUtil.getErrorLogEntity(ex.getClass().getSimpleName(), ex.getMessage()));
            logger.info("ErrorLog record inserted with {}", errorLogEntity.getId());
        } catch (Exception exception) {
            logger.error("Exception thrown while storing error records {}", exception.getMessage());
        }

    }
}
