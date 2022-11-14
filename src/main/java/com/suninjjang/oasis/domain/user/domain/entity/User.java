package com.suninjjang.oasis.domain.user.domain.entity;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Document(collation = "user")
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    @Nullable()
    private String username;

    private String email;

    private String password;

    @Nullable()
    private String datedDate;

    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User(String id, String refreshToken){
        this.id = id;
        this.refreshToken = refreshToken;
    }


}
