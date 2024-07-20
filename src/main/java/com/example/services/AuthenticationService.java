package com.example.services;

import com.example.dto.JwtAuthenticationResponse;
import com.example.dto.RefreshTokenRequest;
import com.example.dto.SignInRequest;
import com.example.dto.SignUpRequest;
import com.example.models.User;

public interface AuthenticationService {
    public User signup(SignUpRequest signUpRequest);
    public JwtAuthenticationResponse signin(SignInRequest signInRequest);
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
