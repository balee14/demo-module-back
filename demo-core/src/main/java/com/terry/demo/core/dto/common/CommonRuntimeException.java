package com.terry.demo.core.dto.common;

public class CommonRuntimeException extends RuntimeException {

    public CommonRuntimeException(String errorCode) {
        super(errorCode);
    }

}
