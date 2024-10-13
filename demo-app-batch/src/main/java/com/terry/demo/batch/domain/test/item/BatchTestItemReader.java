package com.terry.demo.batch.domain.test.item;

import com.terry.demo.core.entity.PfTestBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class BatchTestItemReader implements ItemReader<PfTestBatch> {

    private static final Logger logger = LoggerFactory.getLogger(BatchTestItemReader.class);

    private int count = 0;

    @Override
    public PfTestBatch read() {
        int MAX_COUNT = 1000;
        if (count < MAX_COUNT) {
            count++;
            logger.info("read count -> {}", count);
            return createPfTestBatch(count);
        }
        return null; // null 반환 시 읽기 종료
    }

    private PfTestBatch createPfTestBatch(int count) {
        return PfTestBatch.builder()
                .idEmail("test" + count + "@pomg.co.kr")
                .description("test-" + count)
                .build();
    }

}
