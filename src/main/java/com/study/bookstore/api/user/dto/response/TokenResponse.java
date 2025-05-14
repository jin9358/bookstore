package com.study.bookstore.api.user.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
