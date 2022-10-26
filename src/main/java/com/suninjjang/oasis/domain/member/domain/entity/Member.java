package com.suninjjang.oasis.domain.member.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "user")
@Data
public class Member {
    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    public Member() {}

    public Member(String username, String email){
        this.email = email;
        this.username = username;
    }
}
