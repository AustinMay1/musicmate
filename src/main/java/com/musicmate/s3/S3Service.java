package com.musicmate.s3;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {
    private final S3Client CLIENT;

    public S3Service(S3Client client) {
        this.CLIENT = client;
    }

    public void uploadObject(String bucketName, String key, byte[] file) {
        PutObjectRequest objRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        CLIENT.putObject(objRequest, RequestBody.fromBytes(file));
    }

    public byte[] downloadObject(String bucketName, String key) {
        GetObjectRequest objRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        ResponseInputStream<GetObjectResponse> response = CLIENT.getObject(objRequest);

        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
