package com.prokarma.poc.consumer.kafka;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokarma.poc.consumer.Utility;
import com.prokarma.poc.consumer.service.ConsumerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerKafkaTest {
    @InjectMocks
    KafkaConsumer kafkaConsumer;
    @Mock
    ConsumerServiceImpl consumerService;

    @Test
    public void testConsume() {
        String input = "{\"customerNumber\":\"C000000002\",\"firstName\":\"bargavakumar\",\"lastName\":\"akavarammm\",\"birthDate\":\"18-05-1991\",\"country\":\"INDIA\",\"countryCode\":\"IN\",\"mobileNumber\":\"9989922802\",\"email\":\"adotbhargav@gmail.com\",\"customerStatus\":\"OPEN\",\"address\":{\"addressLine1\":\"Plot no 28\",\"addressLine2\":\"Lalitha nagar\",\"street\":\"Sai nagar\",\"postalCode\":\"50068\"}}";
        kafkaConsumer.consume(Utility.constructObjectNode(input));
        Mockito.verify(consumerService, Mockito.times(1)).storeCustomerDetails(ArgumentMatchers.any(ObjectNode.class));
    }
}
