package com.suninjjang.oasis.domain.auth.services;

import com.suninjjang.oasis.domain.auth.dto.LoginDto;
import com.suninjjang.oasis.domain.auth.dto.request.LoginRequest;

public interface LoginService {
    LoginDto login(LoginRequest loginRequest);
}
