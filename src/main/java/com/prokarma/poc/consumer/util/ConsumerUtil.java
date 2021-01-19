package com.prokarma.poc.consumer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokarma.poc.consumer.constants.ConsumerConstant;

public class ConsumerUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private ConsumerUtil() {

    }

    public static ObjectNode customerDetailsMasker(ObjectNode messageNode) {
        ObjectNode objectNode = objectMapper.convertValue(messageNode, ObjectNode.class);
        objectNode.put(ConsumerConstant.CUSTOMER_DETAILS_EMAIL, objectNode.get(ConsumerConstant.CUSTOMER_DETAILS_EMAIL).textValue().replaceAll("(\\w{4})(\\w+)(@.*)", "****$2$3"));
        objectNode.put(ConsumerConstant.CUSTOMER_DETAILS_BIRTH_DATE, objectNode.get(ConsumerConstant.CUSTOMER_DETAILS_BIRTH_DATE).textValue().replaceAll("^[0-3][0-9]-[0-3][0-9]", "**-**"));
        String customerNumber = objectNode.get(ConsumerConstant.CUSTOMER_DETAILS_CUSTOMER_NUMBER).textValue();
        String last4chars = customerNumber.substring((customerNumber.length() - ConsumerConstant.MASK_CUSTOMER_NUMBER_LAST_DIGITS)).replaceAll("\\w", "*");
        String first6chars = customerNumber.substring(0, (customerNumber.length() - ConsumerConstant.MASK_CUSTOMER_NUMBER_LAST_DIGITS));
        objectNode.put(ConsumerConstant.CUSTOMER_DETAILS_CUSTOMER_NUMBER, first6chars.concat(last4chars));
        return objectNode;
    }
}
