package com.prokarma.poc.consumer.constants;

public class ConsumerConstant {

    public static final String TOPIC = "customer-details";
    public static final String GROUP_ID = "testGroupId";
    public static final String CONTAINER_Factory = "concurrentKafkaListenerContainerFactory";
    public static final String KAFKA_BOOTSTRAP_URL = "localhost:9002";
    public static final String KAFKA_CONSUMER_GROUP_ID = "testGroupId";
    public static final String MASK_CUSTOMER_NUMBER = "customerNumber";
    public static final String MASK_EMAIL = "email";
    public static final String MASK_BIRTHDATE = "birthDate";
    public static final int MASK_EMAIL_DIGITS = 4;
    public static final int MASK_CUSTOMER_NUMBER_LAST_DIGITS = 4;
    public static final int MASK_EMAIL_DIGITS_LAST_DIGITS = 4;

    private ConsumerConstant() {
    }
}
