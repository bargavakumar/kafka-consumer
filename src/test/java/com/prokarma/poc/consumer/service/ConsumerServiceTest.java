package com.prokarma.poc.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokarma.poc.consumer.dao.ConsumerDAOImpl;
import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import com.prokarma.poc.consumer.kafka.ConsumerKafkaTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerServiceTest {
    private static Logger logger = LoggerFactory.getLogger(ConsumerKafkaTest.class);
    @InjectMocks
    ConsumerServiceImpl consumerService;
    @Mock
    ConsumerDAOImpl consumerDAO;

    @Test
    public void testStoreCustomerDetails() {
        try {
            consumerService.storeCustomerDetails(new ObjectMapper().readValue("{}", ObjectNode.class));
            Mockito.verify(consumerDAO, Mockito.times(1)).storeCustomerDetails(ArgumentMatchers.any(CustomerDetailsEntity.class));
        } catch (JsonProcessingException jsonProcessingException) {
            logger.error(jsonProcessingException.getMessage());
        }


    }

}
