package com.example.privateadsystem.controller;

import com.example.privateadsystem.exception.DataBaseException;
import com.example.privateadsystem.exception.ErrorResponse;
import com.example.privateadsystem.exception.NotEntityException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({DataBaseException.class})
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleDataBaseException(DataBaseException dataBaseException) {
        return ErrorResponse.builder()
                .message(dataBaseException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler({NotEntityException.class})
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleDataBaseException(NotEntityException notEntityException) {
        return ErrorResponse.builder()
                .message(notEntityException.getMessage())
                .status(BAD_REQUEST)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundException(ChangeSetPersister.NotFoundException notFoundException) {
        return ErrorResponse.builder()
                .message(notFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(now())
                .build();
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ErrorResponse handleNotFoundException(AuthException authException) {
        return ErrorResponse.builder()
                .message(authException.getMessage())
                .status(UNAUTHORIZED)
                .timestamp(now())
                .build();
    }
}
