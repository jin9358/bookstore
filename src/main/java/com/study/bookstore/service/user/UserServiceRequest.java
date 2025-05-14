package com.study.bookstore.service.user;

import com.study.bookstore.domain.user.User;

public record UserServiceRequest(
        String username,
        String email,
        String encodedPassword
) {

    public static UserServiceRequest toService(User user) {
        return new UserServiceRequest(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

}
