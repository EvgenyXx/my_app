package com.example.my_app.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ValidationErrorResponse {

    private LocalDateTime time;

    private HttpStatus httpStatus;

    private List<String> messageErrors;
}
