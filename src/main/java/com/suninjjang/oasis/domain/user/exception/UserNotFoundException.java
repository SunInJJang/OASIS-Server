package com.suninjjang.oasis.domain.user.exception;

import com.suninjjang.oasis.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException{
    private ErrorCode errorCode;
}
