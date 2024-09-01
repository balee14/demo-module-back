package com.terry.demo.core.config.aws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@Log4j2
public class AwsS3BucketOrObject {

    private static S3Client s3Client;
    private static String bucketName;

    public AwsS3BucketOrObject(@Value("${spring.cloud.aws.bucket.static}") String bucketName
        , S3Client s3Client) {
        this.bucketName = bucketName;
        this.s3Client = s3Client;
    }

    /**
     * 파일 리스트 조회
     * @param prefix
     * @return
     */
    public static ListObjectsV2Response getListObjectsV2(String prefix) {

        ListObjectsV2Request listObjectsReqManual = ListObjectsV2Request.builder()
            .bucket(bucketName)
            .prefix(prefix)
            .build();
        return s3Client.listObjectsV2(listObjectsReqManual);

    }

    /**
     * 파일 등록
     * @param key
     * @param multipartFile
     */
    public static void uploadObjectFileToS3(String key, MultipartFile multipartFile) throws IOException {

        // S3 메타데이터 설정
//        metadata.setContentDisposition("attachment; filename*=UTF-8''" + encodedFilename);
//        Map<String, String> metadata = new HashMap<>();
//        metadata.put("author", "Mary Doe");
//        metadata.put("version", "1.0.0.0");

        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        boolean isFile = file.isFile();
        log.info("isFile-->" + isFile);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
//                .metadata()
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
        log.info("s3 -->" + key + " was upload");

    }

    /**
     * 파일 삭제
     * @param key
     */
    public static void deleteObjectFileToS3(String key) {

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
        log.info("s3 -->" + key + " was deleted");

    }



}
