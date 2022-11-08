package com.suninjjang.oasis.domain.auth.utils.impl;

import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import com.suninjjang.oasis.domain.auth.utils.AuthUtils;
import com.suninjjang.oasis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUtilsImpl implements AuthUtils {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenDto generateTokenResponse(String email) {
        String accessToken = jwtTokenProvider.generateAccessToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);
        Integer accessExp = 60 * 15;
        Integer refreshExp = 60 * 60 * 24 * 7;
        return new TokenDto(accessToken,refreshToken,accessExp,refreshExp);
    }
}
