package com.suninjjang.oasis.global.security.exception;

import com.suninjjang.oasis.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExpiredTokenException extends RuntimeException {
     private ErrorCode errorCode;

}
