package com.terry.demo.user.domain.test;

import com.terry.demo.core.dto.test.TestDto;
import com.terry.demo.core.dto.test.TestQueryDto;
import com.terry.demo.core.entity.PfTest;
import com.terry.demo.core.enums.PfTsidEnum;
import com.terry.demo.core.util.PfTsidUtils;
import com.terry.demo.test.repository.TestRepository;
import com.terry.demo.user.domain.test.dto.request.UserTestRequest;
import com.terry.demo.user.domain.test.dto.response.UserTestDtoQueryResponse;
import com.terry.demo.user.domain.test.dto.response.UserTestDtoResponse;
import com.terry.demo.user.domain.test.mapper.UserTestTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class UserTestService {

    private static final Logger logger = LoggerFactory.getLogger(UserTestService.class);

    private final TestRepository testRepository;

    /**
     * admin test 목록 조회
     */
    /*
    @Transactional(readOnly = true)
    public ResponseEntity<?> getUserTestList(TestListRequest testListRequest, Pageable pageable) {

        TestListResponse testListResponse = new TestListResponse();

        Page<TestsDto> testList = testService.getTestList(testListRequest, pageable);
        testListResponse.setTestList(testList.getContent());
        testListResponse.setAllCount(testList.getTotalElements());

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, testListResponse)
            , CommonResponseEntityType.OK.getHttpStatus());

    }
    */

    /**
     * user test 조회
     */
    @Transactional(readOnly = true)
    public UserTestDtoQueryResponse getUserTestById(String testId) {

        TestQueryDto testQueryDto = testRepository.getTestById(testId);

        return  UserTestDtoQueryResponse.builder()
                .testQueryDto(testQueryDto)
                .build();

    }

    /**
     * user 등록
     */
    public UserTestDtoResponse userTestSave(UserTestRequest userTestRequest) {

        userTestRequest.setTestId(PfTsidUtils.tsidStr(PfTsidEnum.TEST.getTsidId()));
        PfTest pfTest = UserTestTo.userTestRequestToPfTest(userTestRequest);

        PfTest pfTestSave = testRepository.save(Objects.requireNonNull(pfTest));

        TestDto testDto = UserTestTo.pfTestToUserTestDto(pfTestSave);

        return UserTestDtoResponse.builder()
                .testDto(testDto)
                .build();

    }

    /**
     * user 등록(파일)
     */
    public UserTestDtoResponse userTestFileSave(UserTestRequest userTestRequest) throws IOException {

        userTestRequest.setTestId(PfTsidUtils.tsidStr(PfTsidEnum.TEST.getTsidId()));
        PfTest pfTest = UserTestTo.userTestRequestToPfTest(userTestRequest);

        PfTest pfTestSave = testRepository.save(Objects.requireNonNull(pfTest));

        TestDto testDto = UserTestTo.pfTestToUserTestDto(pfTestSave);

        /*
        if (!ObjectUtils.isEmpty(pfTest.getMultipartFile())) {

            String fileName = pfTest.getMultipartFile().getOriginalFilename();
            // 초까지 같을 수가 있기 때문에 i를 통한 index 값 추가
            String s3FileName = "document-" + "1" + "-" + "0" + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String fileUrl = "document/" + "1" + "/";
            String s3FileKey = fileUrl + s3FileName;
            // s3파일 업로드
            //AwsS3BucketOrObject.uploadObjectFileToS3(s3FileKey, pfTest.getMultipartFile());

        }
        */
        return UserTestDtoResponse.builder()
                .testDto(testDto)
                .build();

    }

    /**
     * user 수정
     */
    /*
    public ResponseEntity<?> userTestUpdate(TestUpdateRequest testUpdateRequest) {

        TestUpdateResponse testUpdateResponse = new TestUpdateResponse();

        Long testCnt = testService.testUpdate(testUpdateRequest);
        testUpdateResponse.setTestCnt(testCnt);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, testUpdateResponse)
            , CommonResponseEntityType.OK.getHttpStatus());

    }
    */

    /**
     * user 수정
     */
    /*
    public ResponseEntity<?> userTestPwdUpdate(TestPwdUpdateRequest testPwdUpdateRequest) {

        TestUpdateResponse testUpdateResponse = new TestUpdateResponse();

        Long testCnt = testService.testPwdUpdate(testPwdUpdateRequest);
        testUpdateResponse.setTestCnt(testCnt);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, testUpdateResponse)
            , CommonResponseEntityType.OK.getHttpStatus());

    }
    */

}

