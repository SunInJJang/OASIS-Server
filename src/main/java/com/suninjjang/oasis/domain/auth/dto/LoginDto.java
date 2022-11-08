package com.suninjjang.oasis.domain.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class LoginDto {
    private final TokenDto tokenDto;
    private final HttpStatus httpStatus;
}
