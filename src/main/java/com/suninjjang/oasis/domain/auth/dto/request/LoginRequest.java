package com.suninjjang.oasis.domain.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@Getter
public class LoginRequest {
    private final String userId;
    private final String password;

    @JsonCreator
    public LoginRequest(String userId, String password){
        this.userId = userId;
        this.password = password;
    }
}
