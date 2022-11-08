package com.suninjjang.oasis.domain.auth.utils.impl;

import com.suninjjang.oasis.domain.auth.dto.LoginDto;
import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import com.suninjjang.oasis.domain.auth.dto.response.LoginResponse;
import com.suninjjang.oasis.domain.auth.dto.response.TokenResponse;
import com.suninjjang.oasis.domain.auth.utils.AuthConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthConverterImpl implements AuthConverter {
    @Override
    public LoginResponse toLoginResponse(LoginDto loginDto) {
        TokenResponse tokenResponse = toTokenResponse(loginDto.getTokenDto());
        return new LoginResponse(tokenResponse, loginDto.getHttpStatus());
    }

    @Override
    public TokenResponse toTokenResponse(TokenDto tokenDto) {
        return new TokenResponse(tokenDto.getAccessToken(), tokenDto.getRefreshToken(), tokenDto.getAccessExp(), tokenDto.getRefreshExp());
    }
}
