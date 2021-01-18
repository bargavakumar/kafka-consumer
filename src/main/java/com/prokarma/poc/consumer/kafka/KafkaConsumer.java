package com.prokarma.poc.consumer.kafka;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokarma.poc.consumer.constants.ConsumerConstant;
import com.prokarma.poc.consumer.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private ConsumerService consumerService;

    @Autowired
    KafkaConsumer(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @KafkaListener(topics = ConsumerConstant.TOPIC, groupId = ConsumerConstant.GROUP_ID, containerFactory = ConsumerConstant.CONTAINER_Factory)
    public void consume(@Payload ObjectNode objectNode) {
        try {
            if (objectNode != null) {
                logger.info("Consumed data from topic {}", objectNode);
                consumerService.storeCustomerDetails(objectNode);
            }
        } catch (Exception ex) {
            logger.error("Exception thrown while saving Customer details {} : ", ex.getMessage());
        }
    }
}
