package com.suninjjang.oasis.domain.auth.services.impl;

import com.suninjjang.oasis.domain.auth.dto.request.SignupRequest;
import com.suninjjang.oasis.domain.auth.services.SignupService;
import com.suninjjang.oasis.domain.user.dao.UserRepository;
import com.suninjjang.oasis.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupRequest signupRequest) {
        if(userRepository.existsUserById(signupRequest.getUserId())){
            User user = User.builder()
                    .id(signupRequest.getUserId())
                    .password(passwordEncoder.encode(signupRequest.getPassword()))
                    .email(signupRequest.getEmail())
                    .datedDate(signupRequest.getDatedDate())
                    .build();
            userRepository.save(user);
        }
    }
}
