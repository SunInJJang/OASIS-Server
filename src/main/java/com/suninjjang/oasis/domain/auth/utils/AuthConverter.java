package com.suninjjang.oasis.domain.auth.utils;

import com.suninjjang.oasis.domain.auth.dto.LoginDto;
import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import com.suninjjang.oasis.domain.auth.dto.response.LoginResponse;
import com.suninjjang.oasis.domain.auth.dto.response.TokenResponse;

public interface AuthConverter {
    LoginResponse toLoginResponse(LoginDto loginDto);
    TokenResponse toTokenResponse(TokenDto tokenDto);
}
