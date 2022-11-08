package com.suninjjang.oasis.domain.user.utils.Impl;

import com.suninjjang.oasis.domain.user.dao.UserRepository;
import com.suninjjang.oasis.domain.user.domain.entity.User;
import com.suninjjang.oasis.domain.user.exception.UserNotFoundException;
import com.suninjjang.oasis.global.error.ErrorCode;
import com.suninjjang.oasis.global.util.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUtilsImpl implements UserUtils {
    private final UserRepository userRepository;
    @Override
    public User getCurrentUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserById(id);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
