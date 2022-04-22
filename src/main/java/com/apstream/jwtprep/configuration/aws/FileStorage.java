package com.apstream.jwtprep.configuration.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FileStorage {

    private final AmazonS3 amazonS3;

//    @Value("${s3.bucket.name}")
//    private String bucketName;

    public void upLoad(File file, String fileName){

           amazonS3.putObject("trippalimages", fileName, file);
    }

    private File convertMultiPartToFile(MultipartFile file ) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;

    }

    private String generateFileName(MultipartFile file){
        return System.currentTimeMillis() + "-" + file.getOriginalFilename();
    }

    public String upLoadFile(MultipartFile multipartFile){

        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = "https://trippalimages.s3.us-east-2.amazonaws.com" + "/" + fileName;
            upLoad( file, fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;

    }
}
