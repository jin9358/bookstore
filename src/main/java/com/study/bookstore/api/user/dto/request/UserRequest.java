package com.study.bookstore.api.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequest(
        @NotBlank
        String username,
        @Email(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        String email,
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$")
        String password
) {
}
