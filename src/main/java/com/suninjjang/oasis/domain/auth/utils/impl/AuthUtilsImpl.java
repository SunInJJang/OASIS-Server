package com.suninjjang.oasis.domain.auth.utils.impl;

import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import com.suninjjang.oasis.domain.auth.dto.request.LoginRequest;
import com.suninjjang.oasis.domain.auth.utils.AuthUtils;
import com.suninjjang.oasis.domain.user.dao.UserRepository;
import com.suninjjang.oasis.domain.user.domain.entity.User;
import com.suninjjang.oasis.domain.user.exception.UserNotFoundException;
import com.suninjjang.oasis.global.error.ErrorCode;
import com.suninjjang.oasis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUtilsImpl implements AuthUtils {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public TokenDto generateTokenResponse(String email) {
        String accessToken = jwtTokenProvider.generateAccessToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);
        Integer accessExp = 60 * 15;
        Integer refreshExp = 60 * 60 * 24 * 7;
        return new TokenDto(accessToken,refreshToken,accessExp,refreshExp);
    }

    @Override
    public LoginRequest generateLoginResponse(String id, String password){
        Optional<User> user = userRepository.findUserById(id);
        return user.map(value -> new LoginRequest(value.getId(), value.getPassword())).orElseThrow(()
                -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
