package com.apstream.jwtprep.configuration.aws;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {



    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    @Value("${s3.region.name}")
    private String s3Region;


    @Bean
    public AmazonS3 s3(){


        AWSCredentials awsCredentials =
                new BasicAWSCredentials(this.accessKey, this.secretKey);

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(this.s3Region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }


}
