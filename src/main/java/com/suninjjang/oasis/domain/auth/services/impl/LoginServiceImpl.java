package com.suninjjang.oasis.domain.auth.services.impl;

import com.suninjjang.oasis.domain.auth.dto.LoginDto;
import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import com.suninjjang.oasis.domain.auth.dto.request.LoginRequest;
import com.suninjjang.oasis.domain.auth.services.LoginService;
import com.suninjjang.oasis.domain.auth.utils.AuthUtils;
import com.suninjjang.oasis.domain.user.dao.UserRepository;
import com.suninjjang.oasis.domain.user.domain.entity.User;
import com.suninjjang.oasis.domain.user.exception.UserNotFoundException;
import com.suninjjang.oasis.global.error.ErrorCode;
import com.suninjjang.oasis.global.security.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final AuthUtils authUtils;
    @Override
    public LoginDto login(LoginRequest loginRequest) {
        HttpStatus httpStatus;
        try{
            LoginRequest login = authUtils.generateLoginResponse(loginRequest.getUserId(), loginRequest.getPassword());

            // 토큰 생성
            String userId = login.getUserId();
            TokenDto tokenResponse = authUtils.generateTokenResponse(userId);

            //유저가 존재할때
            if (userRepository.existsUserById(userId)) {
                User user = userRepository.findUserById(userId).orElseThrow();
                user.updateRefreshToken(tokenResponse.getRefreshToken());
                httpStatus = HttpStatus.OK;
            } else { //유저가 존재하지 않을때
                httpStatus = HttpStatus.CREATED;
                User user = new User(userId, tokenResponse.getRefreshToken());
                userRepository.save(user);
            }
            return new LoginDto(tokenResponse, httpStatus);
        }catch (InvalidTokenException | UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
