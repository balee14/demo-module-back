package com.terry.demo.controller;

import com.terry.demo.service.test.AdminTestFileService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin/v1")
public class AdminTestFileController {

    /**
     * 테스트 파일 관리
     */

    private final AdminTestFileService adminTestFileService;

    /**
     * 파일 조회
     *
     * @param
     * @return
     */
    @GetMapping("/file/files")
    public ResponseEntity<?> adminTestFiles() {
        return adminTestFileService.adminTestFiles();
    }

    /**
     * 파일 등록
     * @param
     * @return
     */
    @PostMapping("/file")
    public ResponseEntity<?> adminTestFileSave(@RequestPart(value = "fileNm", required = false) String fileNm
            , @RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile) throws IOException {
        return adminTestFileService.adminTestFileSave(fileNm, multipartFile);
    }

    /**
     * 파일 삭제
     *
     * @param
     * @return
     */
    @DeleteMapping("/file")
    public ResponseEntity<?> adminTestFileDelete(@RequestPart(value = "fileNm", required = false) String fileNm) {
        return adminTestFileService.adminTestFileDelete(fileNm);
    }




}


