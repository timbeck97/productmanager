package com.productmanager.config;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageSender {

    @Value("${aws.queueName}")
    private String queueName;

    private final AmazonSQS amazonSQSClient;
    private final ObjectMapper objectMapper;
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    public MessageSender(AmazonSQS amazonSQSClient, ObjectMapper objectMapper) {
        this.amazonSQSClient = amazonSQSClient;
        this.objectMapper = objectMapper;
    }

    public void publishMessage(Object message) {
        try {
            System.out.println("queueName: " + queueName);
            GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl(queueName);
            String obj = objectMapper.writeValueAsString(message);
            log.info("Message sent to queue: {}", obj);
            var result = amazonSQSClient.sendMessage(queueUrl.getQueueUrl(), obj);
        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }

    }
}
