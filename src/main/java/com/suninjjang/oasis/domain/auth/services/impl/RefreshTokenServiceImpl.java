package com.suninjjang.oasis.domain.auth.services.impl;

import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import com.suninjjang.oasis.domain.auth.services.RefreshTokenService;
import com.suninjjang.oasis.domain.auth.utils.AuthUtils;
import com.suninjjang.oasis.domain.user.domain.entity.User;
import com.suninjjang.oasis.global.error.ErrorCode;
import com.suninjjang.oasis.global.security.exception.InvalidTokenException;
import com.suninjjang.oasis.global.security.jwt.JwtTokenProvider;
import com.suninjjang.oasis.global.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthUtils authUtils;
    private final UserUtils userUtils;

    @Override
    public TokenDto refresh(String refreshToken) {
        String id = jwtTokenProvider.extractIdFromRefreshToken(refreshToken);
        User user = userUtils.getUserById(id);
        if(!refreshToken.equals(user.getRefreshToken())) throw new InvalidTokenException(ErrorCode.INVALID_TOKEN_EXCEPTION);
        return authUtils.generateTokenResponse(id);
    }
}
