package com.suninjjang.oasis.domain.auth.services.impl;

import com.suninjjang.oasis.domain.auth.services.LogoutService;
import com.suninjjang.oasis.global.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {
    private final UserUtils userUtils;

    @Override
    public void logout() {
        userUtils.getCurrentUser().updateRefreshToken(null);
    }
}
