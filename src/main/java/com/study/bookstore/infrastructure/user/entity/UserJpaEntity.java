package com.study.bookstore.infrastructure.user.entity;

import com.study.bookstore.global.common.BaseEntity;
import com.study.bookstore.service.user.UserServiceRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public static UserJpaEntity toEntity(UserServiceRequest request) {
        return UserJpaEntity.builder()
                .username(request.username())
                .email(request.email())
                .password(request.encodedPassword())
                .build();
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
