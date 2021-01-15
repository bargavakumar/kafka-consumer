package com.prokarma.poc.consumer.repository;

import com.prokarma.poc.consumer.entitties.ErrorLogEntity;
import org.springframework.data.repository.CrudRepository;

public interface ErrorLogRepository extends CrudRepository<ErrorLogEntity, Long> {
}
