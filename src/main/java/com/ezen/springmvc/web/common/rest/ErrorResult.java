package com.ezen.springmvc.web.common.rest;

import lombok.Getter;
import org.springframework.validation.Errors;
import java.util.List;

@Getter
public class ErrorResult {

    private List<ErrorField> errorFields;

    public ErrorResult(Errors errors) {
        errorFields = errors.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    return ErrorField.builder()
                            .objectName(fieldError.getObjectName())
                            .field(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                            .build();
                })
                .toList();
    }
}
