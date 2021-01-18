package com.prokarma.poc.consumer.repository;

import com.prokarma.poc.consumer.entitties.CustomerDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetailsEntity, Long> {
}
