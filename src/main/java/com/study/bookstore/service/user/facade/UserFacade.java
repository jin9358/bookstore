package com.study.bookstore.service.user.facade;

import com.study.bookstore.api.user.dto.request.UserRequest;
import com.study.bookstore.domain.user.User;
import com.study.bookstore.domain.user.UserMapper;
import com.study.bookstore.global.error.DuplicatedUserException;
import com.study.bookstore.global.facade.BCryptPasswordEncoderFacade;
import com.study.bookstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoderFacade passwordEncoderFacade;

    @Transactional
    public Long createUser(UserRequest controllerDto) {
        User domain = userMapper.toDomain(controllerDto);
        String encode = passwordEncoderFacade.encode(controllerDto.password());
        domain.encodedPassword(encode);

        validateDuplicate(domain.getEmail());
        return userService.createUser(domain);
    }

    public void validateDuplicate(String email) {
        userService.findByEmail(email).ifPresent(user -> {
            throw new DuplicatedUserException("중복 이메일");
        });
    }

    // updateUser

    // deleteUser

    // getUserById

}
