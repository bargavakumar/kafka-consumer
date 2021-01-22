package com.prokarma.poc.consumer;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prokarma.poc.consumer.kafka.KafkaConsumer;
import com.prokarma.poc.consumer.repository.CustomerDetailsRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class ConsumerApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(ConsumerApplicationTest.class);

    @Autowired
    KafkaTemplate<String, ObjectNode> kafkaTemplate;

    @Autowired
    KafkaConsumer kafkaConsumer;

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    @Value("${consumer.kafka-topic}")
    private String topic;

    @Test
    void testConsumerToStoreCustomerDetailsEntity() {
        String input = "{\"customerNumber\":\"C000000002\",\"firstName\":\"bargavakumar\",\"lastName\":\"akavarammm\",\"birthDate\":\"18-05-1991\",\"country\":\"INDIA\",\"countryCode\":\"IN\",\"mobileNumber\":\"9989922802\",\"email\":\"adotbhargav@gmail.com\",\"customerStatus\":\"O\",\"address\":{\"addressLine1\":\"Plot no 28\",\"addressLine2\":\"Lalitha nagar\",\"street\":\"Sai nagar\",\"postalCode\":\"50068\"}}";
        kafkaTemplate.send(topic, Utility.constructObjectNode(input));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    void testConsumerToStoreErrorLogWhenExceptionOccursOnStoreCustomerDetailsService() {
        String input = "{\"customerNumber\":\"C000000002\",\"firstName\":\"bargavakumar\",\"lastName\":\"akavarammm\",\"birthDate\":\"18-05-1991\",\"country\":\"INDIA\",\"countryCode\":\"IN\",\"mobileNumber\":\"9989922802\",\"email\":\"adotbhargav@gmail.com\",\"customerStatus\":\"OPEN\",\"address\":{\"addressLine1\":\"Plot no 28\",\"addressLine2\":\"Lalitha nagar\",\"street\":\"Sai nagar\",\"postalCode\":\"50068\"}}";
        kafkaTemplate.send(topic, Utility.constructObjectNode(input));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
