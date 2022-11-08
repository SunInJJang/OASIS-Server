package com.suninjjang.oasis.domain.auth.dto.response;

import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class LoginResponse {
    private final TokenResponse tokenResponse;
    private final HttpStatus httpStatus;
}
