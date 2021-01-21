package com.prokarma.poc.consumer.kafka;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokarma.poc.consumer.service.ConsumerService;
import com.prokarma.poc.consumer.util.ConsumerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaConsumer {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private ConsumerService consumerService;

    // For integration test purpose only
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    KafkaConsumer(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${consumer.kafka-topic}")
    public void consume(ObjectNode objectNode) {
        try {
            if (objectNode != null) {
                logger.info("Consumed data from topic {}", ConsumerUtil.customerDetailsMasker(objectNode));
                consumerService.storeCustomerDetails(objectNode);
            }
        } catch (Exception ex) {
            logger.error("Exception thrown while saving Customer details {} : ", ex.getMessage());
        }
    }
}
