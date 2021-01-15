package com.prokarma.poc.consumer.util;

import com.prokarma.poc.consumer.entitties.ErrorLogEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsumerUtil {

    public ErrorLogEntity getErrorLogEntity(String errorType, String errorDescription) {
        ErrorLogEntity errorLogEntity = new ErrorLogEntity();
        errorLogEntity.setErrorType(errorType);
        errorLogEntity.setErrorDescription(errorDescription);
        return errorLogEntity;
    }
}
