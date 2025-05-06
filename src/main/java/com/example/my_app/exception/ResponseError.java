package com.example.my_app.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ResponseError {

    private LocalDateTime time;

    private HttpStatus httpStatus;

    private String message;
}
