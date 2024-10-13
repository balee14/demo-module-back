package com.terry.demo;

import com.terry.demo.core.enums.PfTsidEnum;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class DemoAppBatchApplicationTests {

    @Test
    void contextLoads() {

        log.info("test -> {}", PfTsidEnum.TEST.getTsidId());

    }

}
