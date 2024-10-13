package com.terry.demo.batch.domain.test.item;

import com.terry.demo.core.entity.PfTestBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TestBatchItemProcessor implements ItemProcessor<PfTestBatch, PfTestBatch> {

    private static final Logger logger = LoggerFactory.getLogger(TestBatchItemProcessor.class);

    @Override
    public PfTestBatch process(PfTestBatch pfTestBatch) {
        logger.info("item process");
        return pfTestBatch;
    }

}
