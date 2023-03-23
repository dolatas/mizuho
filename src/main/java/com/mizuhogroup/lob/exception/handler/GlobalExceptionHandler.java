package com.mizuhogroup.lob.exception.handler;

import com.mizuhogroup.lob.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponseDto handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorResponseDto("Something went wrong.");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleOrderNotFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(e.getMessage()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponseDto> handleBindException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ErrorResponseDto("Validation failed, check application logs for more details."));
    }
}
