package com.study.bookstore.global.facade;

public interface PasswordEncoderFacade {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
