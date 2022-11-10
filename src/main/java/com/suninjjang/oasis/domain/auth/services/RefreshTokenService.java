package com.suninjjang.oasis.domain.auth.services;

import com.suninjjang.oasis.domain.auth.dto.TokenDto;

public interface RefreshTokenService {
    TokenDto refresh(String refreshToken);
}
