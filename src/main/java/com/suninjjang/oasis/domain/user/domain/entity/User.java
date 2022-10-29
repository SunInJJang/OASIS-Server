package com.suninjjang.oasis.domain.user.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "user")
@Data
public class User {
    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    public User() {}

    public User(String username, String email){
        this.email = email;
        this.username = username;
    }
}
