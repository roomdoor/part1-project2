package com.example.websample.Exception;

import com.example.websample.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> handlerIllegalAccessException(IllegalAccessException e) {
        log.error("IllegalAccessException is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .header("newHeader", "Some Value")
                .body(new ErrorResponse(
                        ErrorCode.TOO_BIG_ID_ERROR,
                        "IllegalAccessException is occurred.")
                );
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handlerWebSampleException(WebSampleException e) {
        log.error("WebSampleException is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.INSUFFICIENT_STORAGE)
                .body(new ErrorResponse(
                        e.getErrorCode(),
                        e.getMessage())
                );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception e) {
        log.error("Exception is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        e.getMessage())
                );
    }
}
