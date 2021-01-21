package com.prokarma.poc.consumer.service;

import com.prokarma.poc.consumer.Utility;
import com.prokarma.poc.consumer.dao.ConsumerDAOImpl;
import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
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
