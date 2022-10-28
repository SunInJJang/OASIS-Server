package com.suninjjang.oasis.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    USER_NOT_FOUND(404,"User Not Found"),
    EXPIRED_TOKEN_EXCEPTION(401,"Expired Token"),
    INVALID_TOKEN_EXCEPTION(401,"Invalid Token");

    private final int status;
    private final String message;
}
