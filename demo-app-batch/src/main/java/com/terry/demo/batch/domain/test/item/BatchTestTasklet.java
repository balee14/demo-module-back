package com.terry.demo.batch.domain.test.item;

import com.terry.demo.core.entity.PfTestBatch;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BatchTestTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(BatchTestTasklet.class);

    private final JdbcTemplate jdbcTemplate;
    private static final int MAX_COUNT = 1000;
    private static final int BATCH_SIZE = 10; // 배치 사이즈 설정

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        insertBatch(generateData());
        return RepeatStatus.FINISHED;
    }

    /**
     *
     * @return
     */
    private List<PfTestBatch> generateData() {
        List<PfTestBatch> data = new ArrayList<>();
        for (int i = 1; i <= MAX_COUNT; i++) {
            logger.info("i -> {}", i);
            data.add(PfTestBatch.builder()
                    .idEmail("test" + i + "@pomg.co.kr")
                    .description("test-" + i)
                    .build());
        }
        return data;
    }

    /**
     *
     * @param batchData
     */
    private void insertBatch(List<PfTestBatch> batchData) {

        String sql = "INSERT INTO pf_test_batch (id_email, description) VALUES (?, ?)";

        for (int start = 0; start < batchData.size(); start += BATCH_SIZE) {

            logger.info("start -> {}", start);

            int end = Math.min(start + BATCH_SIZE, batchData.size());
            List<PfTestBatch> batch = batchData.subList(start, end);

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    PfTestBatch batchItem = batch.get(i);
                    ps.setString(1, batchItem.getIdEmail());               // id_email
                    ps.setString(2, batchItem.getDescription());           // description
                }

                @Override
                public int getBatchSize() {
                    return batch.size(); // 현재 배치 크기 반환
                }
            });

            logger.info("getBatchSize -> {}", batch.size());
        }

    }

}
