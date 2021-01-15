package com.prokarma.poc.consumer.aspect;

import com.prokarma.poc.consumer.service.ConsumerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ConsumerAspect {

    @Autowired
    ConsumerService consumerService;

    @AfterThrowing(value = "execution(* com.prokarma.poc.consumer.dao.ConsumerDAO.storeCustomerDetails*(String)))", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        System.out.println("After Throwing exception in method:" + joinPoint.getSignature());
        System.out.println("Exception is:" + ex.getMessage());
        //consumerService.createErrorRecord(ex);
    }
}
