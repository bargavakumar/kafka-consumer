package com.prokarma.poc.consumer.constants;

public class ConsumerConstant {

    private ConsumerConstant() {

    }

    public static final String TOPIC = "customer-details";
    public static final String GROUP_ID = "testGroupId";
    public static final String CONTAINER_Factory = "concurrentKafkaListenerContainerFactory";
    public static final String KAFKA_BOOTSTRAP_URL = "localhost:9002";
    public static final String KAFKA_CONSUMER_GROUP_ID= "testGroupId";
}
