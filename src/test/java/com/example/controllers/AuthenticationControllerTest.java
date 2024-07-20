package com.example.controllers;

import com.example.dto.JwtAuthenticationResponse;
import com.example.dto.RefreshTokenRequest;
import com.example.dto.SignInRequest;
import com.example.dto.SignUpRequest;
import com.example.models.User;
import com.example.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignup() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setLogin("test@example.com");
        signUpRequest.setPassword("password123");

        User user = new User();
        user.setLogin("test@example.com");

        when(authenticationService.signup(any(SignUpRequest.class))).thenReturn(user);

        ResponseEntity<User> response = authenticationController.signup(signUpRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user.getLogin(), response.getBody().getLogin());
    }

    @Test
    void testSignin() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setLogin("test@example.com");
        signInRequest.setPassword("password123");

        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
        jwtResponse.setToken("fake-jwt-token");
        jwtResponse.setRefreshToken("fake-refresh-token");

        when(authenticationService.signin(any(SignInRequest.class))).thenReturn(jwtResponse);

        ResponseEntity<JwtAuthenticationResponse> response = authenticationController.signin(signInRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(jwtResponse.getToken(), response.getBody().getToken());
        assertEquals(jwtResponse.getRefreshToken(), response.getBody().getRefreshToken());
    }

    @Test
    void testRefresh() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken("fake-refresh-token");

        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
        jwtResponse.setToken("new-jwt-token");
        jwtResponse.setRefreshToken("new-refresh-token");

        when(authenticationService.refreshToken(any(RefreshTokenRequest.class))).thenReturn(jwtResponse);

        ResponseEntity<JwtAuthenticationResponse> response = authenticationController.refresh(refreshTokenRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(jwtResponse.getToken(), response.getBody().getToken());
        assertEquals(jwtResponse.getRefreshToken(), response.getBody().getRefreshToken());
    }
}
