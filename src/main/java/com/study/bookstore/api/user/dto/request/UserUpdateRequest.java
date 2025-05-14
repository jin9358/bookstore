package com.study.bookstore.api.user.dto.request;

public record UserUpdateRequest(
        String username,
        String password
) {
}
