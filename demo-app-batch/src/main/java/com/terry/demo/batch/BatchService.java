package com.terry.demo.batch;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BatchService {
    private final QuartzService quartzService;

    @PostConstruct
    public void init() {
        try {
            quartzService.addSimpleJob(QuartzJob.class, "QuartzJob", "Quartz 잡",null , 10);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
