package com.suninjjang.oasis.global.security.exception;

import com.suninjjang.oasis.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class InvalidTokenException extends RuntimeException {
    private ErrorCode errorCode;
}
