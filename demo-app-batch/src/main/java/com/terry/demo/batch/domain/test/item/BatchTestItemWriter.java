package com.terry.demo.batch.domain.test.item;

import com.terry.demo.core.entity.PfTestBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class BatchTestItemWriter implements ItemWriter<PfTestBatch> {

    private static final Logger logger = LoggerFactory.getLogger(BatchTestItemWriter.class);

    private final JdbcBatchItemWriter<PfTestBatch> itemWriter;

    public BatchTestItemWriter(DataSource dataSource) {
        this.itemWriter = new JdbcBatchItemWriter<>();
        this.itemWriter.setDataSource(dataSource);
        this.itemWriter.setSql("INSERT INTO pf_test_batch (company_code, id_email, description) VALUES (:companyCode, :idEmail, :description)");
        this.itemWriter.setItemSqlParameterSourceProvider(this::getSqlParameterSource);
        this.itemWriter.afterPropertiesSet(); // Writer 초기화
    }

    private MapSqlParameterSource getSqlParameterSource(PfTestBatch item) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idEmail", item.getIdEmail());
        paramSource.addValue("description", item.getDescription());
        return paramSource;
    }

    @Override
    public void write(Chunk<? extends PfTestBatch> chunk) throws Exception {

        logger.info("chunk -> {}", chunk);
        // 배치로 데이터를 한 번에 DB에 쓰기
        itemWriter.write(chunk);

    }

}
