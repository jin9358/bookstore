package com.study.bookstore.service.user;

import com.study.bookstore.api.user.dto.request.LoginRequest;
import com.study.bookstore.infrastructure.user.entity.UserJpaEntity;
import com.study.bookstore.infrastructure.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Long authenticate(LoginRequest request) {
        UserJpaEntity user = userJpaRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        if (user.isDeleted()) {
            throw new IllegalArgumentException("User is deleted");
        }

        return user.getId();
    }

    @Transactional(readOnly = true)
    public boolean isUserExists(Long userId) {
        return userJpaRepository.findById(userId)
                .map(user -> !user.isDeleted())
                .orElse(false);
    }
}
