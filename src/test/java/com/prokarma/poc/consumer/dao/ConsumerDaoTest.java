package com.prokarma.poc.consumer.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import com.prokarma.poc.consumer.entitties.ErrorLogEntity;
import com.prokarma.poc.consumer.repository.CustomerDetailsRepository;
import com.prokarma.poc.consumer.repository.ErrorLogRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerDaoTest {
    @InjectMocks
    ConsumerDAOImpl consumerDAO;
    @Mock
    private CustomerDetailsRepository customerDetailsRepository;
    @Mock
    private ErrorLogRepository errorLogRepository;

    @Test
    public void testStoreCustomerDetails() {
        CustomerDetailsEntity customerDetailsEntity = new CustomerDetailsEntity();
        customerDetailsEntity.setId(1L);
        Mockito.when(customerDetailsRepository.save(ArgumentMatchers.any(CustomerDetailsEntity.class))).thenReturn(customerDetailsEntity);
        Long id = consumerDAO.storeCustomerDetails(new CustomerDetailsEntity());
        Assert.assertNotNull(id);
    }

    @Test
    public void testStoreErrorLog() {
        ErrorLogEntity errorLogEntity = new ErrorLogEntity("", "", new ObjectMapper().createObjectNode());
        errorLogEntity.setId(1L);
        Mockito.when(errorLogRepository.save(ArgumentMatchers.any(ErrorLogEntity.class))).thenReturn(errorLogEntity);
        Long id = consumerDAO.storeErrorLog(new ErrorLogEntity("", "", new ObjectMapper().createObjectNode()));
        Assert.assertNotNull(id);
    }


}
