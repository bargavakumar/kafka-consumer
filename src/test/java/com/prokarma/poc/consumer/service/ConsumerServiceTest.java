package com.prokarma.poc.consumer.service;

import com.prokarma.poc.consumer.Utility;
import com.prokarma.poc.consumer.dao.ConsumerDAOImpl;
import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTest {

    @InjectMocks
    ConsumerServiceImpl consumerService;
    @Mock
    ConsumerDAOImpl consumerDAO;

    @Test
    public void testStoreCustomerDetails() {
        consumerService.storeCustomerDetails(Utility.constructObjectNode(""));
        Mockito.verify(consumerDAO, Mockito.times(1)).storeCustomerDetails(ArgumentMatchers.any(CustomerDetailsEntity.class));
    }

}
