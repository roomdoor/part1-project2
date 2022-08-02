package com.example.websample.Exception;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebSampleException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;
}
