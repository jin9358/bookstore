package com.study.bookstore.service.user;

import com.study.bookstore.api.user.dto.request.UserUpdateRequest;
import com.study.bookstore.api.user.dto.response.UserResponse;
import com.study.bookstore.domain.user.User;
import com.study.bookstore.infrastructure.user.entity.UserJpaEntity;
import com.study.bookstore.infrastructure.user.repository.UserJpaRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 회원가입
    @Transactional
    public Long createUser(User domain) {
        UserServiceRequest serviceRequest = UserServiceRequest.toService(domain);
        UserJpaEntity entity = UserJpaEntity.toEntity(serviceRequest);
        return userJpaRepository.save(entity).getId();
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        UserJpaEntity user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (request.username() != null) {
            user.updateUsername(request.username());
        }

        if (request.password() != null) {
            user.updatePassword(passwordEncoder.encode(request.password()));
        }

        return UserResponse.fromEntity(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        UserJpaEntity user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.delete();
        userJpaRepository.save(user);
    }

    public Optional<UserJpaEntity> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    public UserJpaEntity findUserById(Long userId) {
        UserJpaEntity user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.isDeleted()) {
            throw new EntityNotFoundException("User not found");
        }

        return user;
    }
}
