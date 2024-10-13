package com.terry.demo.core.util;


import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidFactory;
import org.springframework.util.StringUtils;

import java.util.random.RandomGenerator;

public class PfTsidUtils {

    public static String tsidStr(String key) {

        // use a random function that returns an int value
        RandomGenerator random = RandomGenerator.getDefault();
        TsidFactory factory = TsidFactory.builder()
                .withRandomFunction(() -> random.nextInt())
                .build();

        // use the factory
        Tsid tsid = factory.create();
        String tsidStr = tsid.toString();

        if (StringUtils.hasText(key)) {
            tsidStr = key + "-" + tsidStr;
        }

        return tsidStr;
    }

}
