package com.terry.demo.service.test;

import com.terry.demo.core.config.aws.AwsS3BucketOrObject;
import com.terry.demo.core.dto.CommonResponseEntity;
import com.terry.demo.core.dto.CommonResponseEntityType;
import com.terry.demo.file.dto.FilesDto;
import com.terry.demo.test.dto.TestsDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@Log4j2
@Service
//@RequiredArgsConstructor
@Transactional
public class AdminTestFileService {

    /**
     *
     * @param
     * @return
     */
    public ResponseEntity<?> adminTestFiles() {

        List<FilesDto> testsDtoList = new ArrayList<>();

        ListObjectsV2Response listObjResponse = AwsS3BucketOrObject.getListObjectsV2("document");
        int i = 0;
        for (S3Object content : listObjResponse.contents()) {
            log.info(" Key: " + content.key() + " size = " + content.size());
            FilesDto filesDto = new FilesDto();
            filesDto.setContentKey(content.key());
            filesDto.setContentSize(content.size());
            testsDtoList.add(filesDto);
            i++;
        }

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, testsDtoList)
                , CommonResponseEntityType.OK.getHttpStatus());

    }



    /**
     * 파일 저장
     * @param
     * @return
     */
    public ResponseEntity<?> adminTestFileSave(String fileNm, MultipartFile multipartFile) throws IOException {

        //File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        //multipartFile.transferTo(file);

//        File file = new File(multipartFile.getOriginalFilename());
//        file.createNewFile();
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(multipartFile.getBytes());
//        fos.close();
//
//        boolean isFile = file.isFile();
//        log.info("isFile-->" + isFile);

        String key = "document" + "/" + "1" + "/" + fileNm;

        AwsS3BucketOrObject.uploadObjectFileToS3(key, multipartFile);
        //S3Scenario.uploadObjectFileToS3(s3Client, bucketName, key, multipartFile);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     *
     * @param
     * @return
     */
    public ResponseEntity<?> adminTestFileDelete(String fileNm) {

        String key = "document" + "/" + "1" + "/" + fileNm;

        AwsS3BucketOrObject.deleteObjectFileToS3(key);


        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK)
                , CommonResponseEntityType.OK.getHttpStatus());

    }



}

