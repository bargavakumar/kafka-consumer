package com.prokarma.poc.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {
    private static Logger logger = LoggerFactory.getLogger(Utility.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectNode constructObjectNode(String input) {
        ObjectNode objectNode = null;
        try {
            if (input.isEmpty()) {
                input = "{}";
            }
            objectNode = objectMapper.readValue(input, ObjectNode.class);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return objectNode;

    }
}
