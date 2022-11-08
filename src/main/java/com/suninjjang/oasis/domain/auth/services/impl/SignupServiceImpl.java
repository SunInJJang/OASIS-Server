package com.suninjjang.oasis.domain.auth.services.impl;

import com.suninjjang.oasis.domain.auth.dto.request.SignupRequest;
import com.suninjjang.oasis.domain.auth.services.SignupService;
import com.suninjjang.oasis.domain.user.dao.UserRepository;
import com.suninjjang.oasis.domain.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void Signup(SignupRequest signupRequest) {
        if(userRepository.findUserById(signupRequest.getUserId()).isPresent()){
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