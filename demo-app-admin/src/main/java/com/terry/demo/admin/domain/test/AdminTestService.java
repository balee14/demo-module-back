package com.terry.demo.admin.domain.test;


import com.terry.demo.admin.domain.test.dto.request.AdminTestRequest;
import com.terry.demo.admin.domain.test.dto.response.AdminTestDtoResponse;
import com.terry.demo.admin.domain.test.dto.response.AdminTestListResponse;
import com.terry.demo.admin.domain.test.mapper.AdminTestMapper;
import com.terry.demo.core.dto.common.CommonCustomType;
import com.terry.demo.core.dto.common.CommonRuntimeException;
import com.terry.demo.core.dto.test.TestDto;
import com.terry.demo.core.entity.PfTest;
import com.terry.demo.core.enums.PfTsidEnum;
import com.terry.demo.core.util.PfTsidUtils;
import com.terry.demo.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class AdminTestService {

    private static final Logger logger = LoggerFactory.getLogger(AdminTestService.class);

    private final TestRepository testRepository;

    /**
     * admin test 목록 조회
     */
    @Transactional(readOnly = true)
    public AdminTestListResponse getAdminTestList(Pageable pageable) {

        List<PfTest> pfTestList = testRepository.findAll();
        List<TestDto> testDtoList = pfTestList.stream()
                .map(pfTest -> TestDto.builder()
                        .testId(pfTest.getTestId())
                        .idEmail(pfTest.getIdEmail())
                        .description(pfTest.getDescription())
                        .build())
                .toList();

        return AdminTestListResponse.builder()
                .testDtoList(testDtoList)
                .build();

    }

    /**
     * admin test 조회
     */
    @Transactional(readOnly = true)
    public AdminTestDtoResponse getAdminTestById(String testId) {

        TestDto testDto = null;

        Optional<PfTest> pfTestOptional = testRepository.findById(testId);
        if (pfTestOptional.isPresent()) {
            PfTest pfTest = pfTestOptional.get();
            testDto = TestDto.builder()
                    .testId(pfTest.getTestId())
                    .idEmail(pfTest.getIdEmail())
                    .description(pfTest.getDescription())
                    .build();
        }

        return AdminTestDtoResponse.builder()
                .testDto(testDto)
                .build();

    }

    /**
     *  admin 등록
     */
    public AdminTestDtoResponse adminTestSave(AdminTestRequest adminTestRequest) {

        adminTestRequest.setTestId(PfTsidUtils.tsidStr(PfTsidEnum.TEST.getTsidId()));
        PfTest pfTest = AdminTestMapper.adminTestRequestToPfTest(adminTestRequest);

        PfTest pfTestSave = testRepository.save(Objects.requireNonNull(pfTest));
        TestDto testDto = AdminTestMapper.pfTestToAdminTestDto(pfTestSave);

        return AdminTestDtoResponse
                .builder()
                .testDto(testDto)
                .build();

    }

    /**
     * admin 등록(파일)
     */
    public AdminTestDtoResponse adminTestFileSave(AdminTestRequest adminTestRequest) throws IOException {

        adminTestRequest.setTestId(PfTsidUtils.tsidStr(PfTsidEnum.TEST.getTsidId()));
        PfTest pfTest = AdminTestMapper.adminTestRequestToPfTest(adminTestRequest);

        PfTest pfTestSave = testRepository.save(Objects.requireNonNull(pfTest));
        TestDto testDto = AdminTestMapper.pfTestToAdminTestDto(pfTestSave);

        /*
        if (!ObjectUtils.isEmpty(pfTest.getMultipartFile())) {

            String fileName = pfTest.getMultipartFile().getOriginalFilename();
            // 초까지 같을 수가 있기 때문에 i를 통한 index 값 추가
            String s3FileName = "document-" + "1" + "-" + "0" + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String fileUrl = "document/" + "1" + "/";
            String s3FileKey = fileUrl + s3FileName;
            // s3파일 업로드
            AwsS3BucketOrObject.uploadObjectFileToS3(s3FileKey, pfTest.getMultipartFile());

        }
        */

        return AdminTestDtoResponse
                .builder()
                .testDto(testDto)
                .build();

    }

    /**
     * admin 수정
     */
    public AdminTestDtoResponse adminTestUpdate(AdminTestRequest adminTestRequest) {

        TestDto testDto = null;
        PfTest pfTest = null;

        String testId = adminTestRequest.getTestId();
        if (ObjectUtils.isEmpty(testId)) {
            pfTest = PfTest.builder()
                    .testId(PfTsidUtils.tsidStr(PfTsidEnum.TEST.getTsidId()))
                    .idEmail(adminTestRequest.getIdEmail())
                    .description(adminTestRequest.getDescription())
                    .build();
            PfTest pfTestSave = testRepository.save(pfTest);

            testDto = TestDto.builder()
                    .testId(pfTestSave.getTestId())
                    .idEmail(pfTestSave.getIdEmail())
                    .description(pfTestSave.getDescription())
                    .build();

        } else {

            pfTest = testRepository.findById(testId)
                    .orElseThrow(() -> new CommonRuntimeException(CommonCustomType.SUCCESSFUL_200_90.name()));

            // todo: 업데이트 구현
//            pfTest.toBuilder()
//                    .idEmail(adminTestRequest.getIdEmail())
//                    .description(adminTestRequest.getDescription())
//                    .build();
        }

        return AdminTestDtoResponse.builder()
                .testDto(testDto)
                .build();

    }

}

