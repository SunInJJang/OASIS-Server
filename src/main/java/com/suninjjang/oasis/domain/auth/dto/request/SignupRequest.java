package com.suninjjang.oasis.domain.auth.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupRequest {
    private final String userId;
    private final String password;
    private final String email;
    private final String nickname;
    private final String datedDate;
}
