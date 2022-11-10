package com.suninjjang.oasis.domain.auth.controller;

import com.suninjjang.oasis.domain.auth.dto.LoginDto;
import com.suninjjang.oasis.domain.auth.dto.TokenDto;
import com.suninjjang.oasis.domain.auth.dto.request.LoginRequest;
import com.suninjjang.oasis.domain.auth.dto.request.SignupRequest;
import com.suninjjang.oasis.domain.auth.dto.response.LoginResponse;
import com.suninjjang.oasis.domain.auth.dto.response.TokenResponse;
import com.suninjjang.oasis.domain.auth.services.LoginService;
import com.suninjjang.oasis.domain.auth.services.LogoutService;
import com.suninjjang.oasis.domain.auth.services.RefreshTokenService;
import com.suninjjang.oasis.domain.auth.services.SignupService;
import com.suninjjang.oasis.domain.auth.utils.AuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final AuthConverter authConverter;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        signupService.signup(signupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginDto loginDto = loginService.login(loginRequest);
        LoginResponse loginResponse = authConverter.toLoginResponse(loginDto);
        TokenResponse tokenResponse = new TokenResponse(loginResponse.getTokenResponse());
        return new ResponseEntity(tokenResponse,loginResponse.getHttpStatus());
    }

    @PutMapping("/logout")
    public ResponseEntity<?> logout(){
        logoutService.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestHeader("Refresh-Token") String refreshToken){
        TokenDto tokenDto = refreshTokenService.refresh(refreshToken);
        TokenResponse tokenResponse = authConverter.toTokenResponse(tokenDto);
        return new ResponseEntity<>(tokenResponse,HttpStatus.OK);
    }
}
