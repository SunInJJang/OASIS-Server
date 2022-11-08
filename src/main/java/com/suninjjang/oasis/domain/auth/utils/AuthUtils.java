package com.suninjjang.oasis.domain.auth.utils;

import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import com.suninjjang.oasis.domain.auth.dto.request.LoginRequest;

public interface AuthUtils {
    TokenDto generateTokenResponse(String email);

    LoginRequest generateLoginResponse(String id, String password);
}
