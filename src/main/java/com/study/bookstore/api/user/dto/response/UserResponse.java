package com.study.bookstore.api.user.dto.response;

import com.study.bookstore.infrastructure.user.entity.UserJpaEntity;

public record UserResponse(
        Long id,
        String username,
        String email
) {
    public static UserResponse fromEntity(UserJpaEntity entity) {
        return new UserResponse(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail()
        );
    }
}
