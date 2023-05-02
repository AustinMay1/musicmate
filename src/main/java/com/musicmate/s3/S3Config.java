package com.musicmate.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class S3Config {
    @Bean
    public S3Client S3Client() {
        return S3Client.builder()
                .region(Region.US_EAST_2)
                .endpointOverride(URI.create("https://s3.us-east-2.amazonaws.com"))
                .forcePathStyle(true)
                .build();
    }
}
