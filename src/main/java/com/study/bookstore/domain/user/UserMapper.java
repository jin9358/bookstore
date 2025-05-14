package com.study.bookstore.domain.user;

import com.study.bookstore.api.user.dto.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserRequest request) {
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .build();
    }
}
