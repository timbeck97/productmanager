package com.productmanager.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class AwsServiceS3 {

    private final Logger log = LogManager.getLogger(this.getClass().getName());

    @Value("${aws.bucketName}")
    private String bucketName;

    private final AmazonS3 amazonS3Client;

    public AwsServiceS3(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public void uploadJsonFile(String message, String fileName){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] bytesToWrite = objectMapper.writeValueAsBytes(message);
            ObjectMetadata omd = new ObjectMetadata();
            omd.setContentLength(bytesToWrite.length);
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(bytesToWrite), omd));
            log.info("File uploaded to S3");
        }catch (Exception e){
            log.error("Error while uploading file to S3: {}", e.getMessage());
        }
    }
}
