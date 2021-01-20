package com.prokarma.poc.consumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokarma.poc.consumer.constants.ConsumerConstant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ConsumerUtilTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCustomerDetailsMasker() {
        String json = "{\"customerNumber\":\"C000000002\",\"firstName\":\"bargavakumar\",\"lastName\":\"akavarammm\",\"birthDate\":\"18-05-1991\",\"country\":\"INDIA\",\"countryCode\":\"IN\",\"mobileNumber\":\"9989922802\",\"email\":\"adotbhargav@gmail.com\",\"customerStatus\":\"OPEN\",\"address\":{\"addressLine1\":\"Plot no 28\",\"addressLine2\":\"Lalitha nagar\",\"street\":\"Sai nagar\",\"postalCode\":\"50068\"}}";
        try {
            ObjectNode newNode = ConsumerUtil.customerDetailsMasker(objectMapper.readValue(json, ObjectNode.class));
            Assert.assertNotNull(newNode);
            Assert.assertEquals("****bhargav@gmail.com", newNode.get(ConsumerConstant.CUSTOMER_DETAILS_EMAIL).textValue());
            Assert.assertEquals("**-**-1991", newNode.get(ConsumerConstant.CUSTOMER_DETAILS_BIRTH_DATE).textValue());
            Assert.assertEquals("C00000****", newNode.get(ConsumerConstant.CUSTOMER_DETAILS_CUSTOMER_NUMBER).textValue());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
