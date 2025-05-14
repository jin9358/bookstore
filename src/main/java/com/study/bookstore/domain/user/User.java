package com.study.bookstore.domain.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private String username;
    private String email;
    private String password;

    public void encodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
