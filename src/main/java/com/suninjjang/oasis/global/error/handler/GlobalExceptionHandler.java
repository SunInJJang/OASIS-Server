package com.suninjjang.oasis.global.error.handler;

import com.suninjjang.oasis.domain.user.exception.UserNotFoundException;
import com.suninjjang.oasis.global.error.ErrorCode;
import com.suninjjang.oasis.global.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(HttpServletRequest request, UserNotFoundException e){
        printException(request,e.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }

    public void printException(HttpServletRequest request, ErrorCode errorCode){
        log.error("URL:"+request.getRequestURI()+"errorCode:"+errorCode);
    }
}
