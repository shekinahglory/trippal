package com.apstream.jwtprep.configuration.aws;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {


    @Bean
    public AmazonS3 s3(){


        AWSCredentials awsCredentials =
                new BasicAWSCredentials("AKIAVILCYVBFW3LJ3PFT", "IA5JeJn+ddvmC8hESOyZToT4ZxodlH3a+keDhGC+");

        return AmazonS3ClientBuilder
                .standard()
                .withRegion("us-east-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }


}
