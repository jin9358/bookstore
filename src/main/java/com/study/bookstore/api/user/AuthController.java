package com.study.bookstore.api.user;

import com.study.bookstore.api.user.dto.request.LoginRequest;
import com.study.bookstore.api.user.dto.response.TokenResponse;
import com.study.bookstore.global.jwt.JwtUtil;
import com.study.bookstore.service.user.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Long userId = authService.authenticate(loginRequest);
        String accessToken = jwtUtil.createAccessToken(userId);
        String refreshToken = jwtUtil.createRefreshToken(userId);
        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }
}
