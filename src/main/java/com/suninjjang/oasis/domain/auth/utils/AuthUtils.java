package com.suninjjang.oasis.domain.auth.utils;

import com.suninjjang.oasis.domain.auth.dto.TokenDto;

public interface AuthUtils {
    TokenDto generateTokenResponse(String email);
}
