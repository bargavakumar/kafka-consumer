package com.prokarma.poc.consumer.kafka;

import com.fasterxml.jackson.databind.JsonNode;
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

    @Autowired
    ConsumerService consumerService;

    @KafkaListener(topics = ConsumerConstant.TOPIC, groupId = ConsumerConstant.GROUP_ID, containerFactory = ConsumerConstant.CONTAINER_Factory)
    public void consume(@Payload JsonNode jsonNode) {
        try {
            if (jsonNode != null) {
                logger.info("Consumed data from topic {}", jsonNode);
                consumerService.storeCustomerDetails(jsonNode);
            }
        } catch (Exception ex) {
            logger.error("Exception thrown while saving Customer details ");
        }
    }
}
