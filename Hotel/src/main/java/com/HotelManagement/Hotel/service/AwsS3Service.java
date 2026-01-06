package com.HotelManagement.Hotel.service;


import com.HotelManagement.Hotel.exception.OurException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

import com.amazonaws.services.s3.model.PutObjectRequest;



@Service
public class AwsS3Service {
    private final String bucketName="anshul-hotel-bucket-images";


     @Value("${aws.s3.access.key}")
    private String awsS3AccessKey;

     @Value("${aws.s3.secrete.key}")
    private String awsS3SecreteKey;


     public String saveImageToS3(MultipartFile photo){
         String s3LocationImage=null;

         try{
             String s2FileName=photo.getOriginalFilename();
             BasicAWSCredentials awsCredentials=new BasicAWSCredentials(awsS3AccessKey,awsS3SecreteKey);
             AmazonS3 s3Client= AmazonS3ClientBuilder.standard()
                     .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                     .withRegion(Regions.EU_NORTH_1)
                     .build();
             InputStream inputStream=photo.getInputStream();
             ObjectMetadata metadata=new ObjectMetadata();
             metadata.setContentType("image/jpg");
             PutObjectRequest putObjectRequest=new PutObjectRequest(bucketName,s2FileName,inputStream,metadata);

             s3Client.putObject(putObjectRequest);
             return "https://" + bucketName + ".s3.amazonaws.com/" + s2FileName;




         } catch(Exception e){
             e.printStackTrace();
             throw new OurException("Unable to upload image to s3 bucket");

         }
     }

}
