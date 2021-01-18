package com.prokarma.poc.consumer.dao;

import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import com.prokarma.poc.consumer.entitties.ErrorLogEntity;
import com.prokarma.poc.consumer.repository.CustomerDetailsRepository;
import com.prokarma.poc.consumer.repository.ErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConsumerDAOImpl implements ConsumerDAO {

    private CustomerDetailsRepository customerDetailsRepository;
    private ErrorLogRepository errorLogRepository;

    @Autowired
    ConsumerDAOImpl(CustomerDetailsRepository customerDetailsRepository, ErrorLogRepository errorLogRepository) {
        this.customerDetailsRepository = customerDetailsRepository;
        this.errorLogRepository = errorLogRepository;
    }

    @Override
    public Long storeCustomerDetails(CustomerDetailsEntity customerDetailsEntity) {
        return customerDetailsRepository.save(customerDetailsEntity).getId();
    }

    @Override
    public Long storeErrorLog(ErrorLogEntity errorLogEntity) {
        return errorLogRepository.save(errorLogEntity).getId();
    }
}
