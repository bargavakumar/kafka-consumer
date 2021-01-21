package com.prokarma.poc.consumer.util;

import com.prokarma.poc.consumer.aspect.ConsumerAspect;
import com.prokarma.poc.consumer.dao.ConsumerDAOImpl;
import com.prokarma.poc.consumer.entitties.ErrorLogEntity;
import org.aspectj.lang.JoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerAspectTest {

    @InjectMocks
    ConsumerAspect consumerAspect;
    @Mock
    private JoinPoint joinPoint;
    @Mock
    private ConsumerDAOImpl consumerDAO;

    @Test
    public void testAfterThrowingAdvice() {
        Mockito.when(joinPoint.getArgs()).thenReturn(new Object[1]);
        consumerAspect.afterThrowingAdvice(joinPoint, new RuntimeException("Test message"));
        Mockito.verify(consumerDAO, Mockito.times(1)).storeErrorLog(ArgumentMatchers.any(ErrorLogEntity.class));
    }
}
