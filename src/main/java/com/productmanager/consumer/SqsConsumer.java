package com.productmanager.consumer;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.productmanager.config.AwsServiceS3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SqsConsumer {
    @Value("${aws.queueName}")
    private String queueName;

    private final Logger log = LogManager.getLogger(this.getClass().getName());

    private final AmazonSQS amazonSQSClient;
    private final AwsServiceS3 awsServiceS3;
    public SqsConsumer(AmazonSQS amazonSQSClient, AwsServiceS3 awsServiceS3) {
        this.amazonSQSClient = amazonSQSClient;
        this.awsServiceS3 = awsServiceS3;
    }

    @Scheduled(fixedDelay = 1000) // It runs every 5 seconds.
    public void consumeMessages() {
        try {
            String queueUrl = amazonSQSClient.getQueueUrl(queueName).getQueueUrl();

            ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);
            if (!receiveMessageResult.getMessages().isEmpty()) {
                Message message = receiveMessageResult.getMessages().get(0);
                log.info("Message from queue: {}", message.getBody());
                String fileName="file_"+System.currentTimeMillis()+".json";
                awsServiceS3.uploadJsonFile(message.getBody(), fileName);
                amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
            }

        } catch (Exception e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }
    }
}
