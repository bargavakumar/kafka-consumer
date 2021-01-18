package com.prokarma.poc.consumer.aspect;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokarma.poc.consumer.dao.ConsumerDAOImpl;
import com.prokarma.poc.consumer.entitties.ErrorLogEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ConsumerAspect {
    private Logger logger = LoggerFactory.getLogger(ConsumerAspect.class);

    private ConsumerDAOImpl consumerDAOImpl;

    @Autowired
    ConsumerAspect(ConsumerDAOImpl consumerDAOImpl) {
        this.consumerDAOImpl = consumerDAOImpl;
    }

    // Error log record with exception details and json payload will be created for every exception thrown on this service
    @AfterThrowing(value = "execution(* com.prokarma.poc.consumer.service.ConsumerService.storeCustomerDetails*(..)) && args(Object))", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        try {
            ErrorLogEntity errorLogEntity = new ErrorLogEntity(ex.getClass().getSimpleName(), ex.getMessage(), (ObjectNode) joinPoint.getArgs()[0]);
            Long id = consumerDAOImpl.storeErrorLog(errorLogEntity);
            logger.info("Error record stored with id {}", id);
        } catch (Exception exception) {
            logger.error("Exception thrown while storing error records {}", exception.getMessage());
        }

    }
}
