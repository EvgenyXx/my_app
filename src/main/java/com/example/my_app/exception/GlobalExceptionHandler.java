package com.example.my_app.exception;

import com.example.my_app.exception.card.DuplicateCardNumberException;
import com.example.my_app.exception.role.DuplicateRoleNameException;
import com.example.my_app.exception.role.NullException;
import com.example.my_app.exception.role.RoleNotFoundException;
import com.example.my_app.exception.token.InvalidTokenException;
import com.example.my_app.exception.user.*;
import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Data
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateNumberPhoneException.class)
    public ResponseEntity<ResponseError> duplicateNumberPhone(DuplicateNumberPhoneException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.CONFLICT)
                        .message(e.getMessage())
                        .build(),HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> validationException(MethodArgumentNotValidException e){

        List<String>errors = e.getBindingResult().getFieldErrors().
                stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        return new ResponseEntity<>(
                ValidationErrorResponse.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .messageErrors(errors)
                        .build(),HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseError> userNotFound(UserNotFoundException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(e.getMessage())
                        .build(),HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ResponseError> duplicateEmail(DuplicateEmailException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.CONFLICT)
                        .message(e.getMessage())
                        .build(),HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ResponseError> invalidCredentials(InvalidCredentialsException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .message(e.getMessage())
                        .build(),HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ResponseError> invalidToken(InvalidTokenException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(e.getMessage())
                        .build(),HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DuplicatePasswordException.class)
    public ResponseEntity<ResponseError> duplicatePassword(DuplicatePasswordException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.CONFLICT)
                        .message(e.getMessage())
                        .build(),HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ResponseError> roleNotFound(RoleNotFoundException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(e.getMessage())
                        .build(),HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateRoleNameException.class)
    public ResponseEntity<ResponseError> duplicateRoleName(DuplicateRoleNameException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.CONFLICT)
                        .message(e.getMessage())
                        .build(),HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NullException.class)
    public ResponseEntity<ResponseError> nullException(NullException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(e.getMessage())
                        .build(),HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DuplicateCardNumberException.class)
    public ResponseEntity<ResponseError> duplicateCardNumber(DuplicateCardNumberException e){
        return new ResponseEntity<>(
                ResponseError.builder()
                        .time(LocalDateTime.now())
                        .httpStatus(HttpStatus.CONFLICT)
                        .message(e.getMessage())
                        .build(),HttpStatus.CONFLICT
        );
    }


}
