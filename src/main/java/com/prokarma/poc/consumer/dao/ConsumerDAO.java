package com.prokarma.poc.consumer.dao;

import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import com.prokarma.poc.consumer.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerDAO {
    @Autowired
    ConsumerRepository consumerRepository;

    public void storeCustomerDetails(CustomerDetailsEntity customerDetailsEntity) {
        throw new RuntimeException();
       /* CustomerDetailsEntity entity = consumerRepository.save(customerDetailsEntity);
        return entity;*/
    }

}
