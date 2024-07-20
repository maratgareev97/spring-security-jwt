package com.example.services.impl;

import com.example.dto.JwtAuthenticationResponse;
import com.example.dto.RefreshTokenRequest;
import com.example.dto.SignInRequest;
import com.example.dto.SignUpRequest;
import com.example.models.Role;
import com.example.models.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.services.JWTservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTservice jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignup() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setLogin("test@example.com");
        signUpRequest.setPassword("password123");

        Role role = new Role();
        role.setName("ROLE_USER");

        User user = new User();
        user.setLogin("test@example.com");
        user.setRoles(Collections.singleton(role));

        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = authenticationService.signup(signUpRequest);

        assertEquals("test@example.com", savedUser.getLogin());
        assertEquals(1, savedUser.getRoles().size());
        assertEquals("ROLE_USER", savedUser.getRoles().iterator().next().getName());
    }

    @Test
    void testSignin() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setLogin("test@example.com");
        signInRequest.setPassword("password123");

        User user = new User();
        user.setLogin("test@example.com");

        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse();
        jwtResponse.setToken("fake-jwt-token");
        jwtResponse.setRefreshToken("fake-refresh-token");

        when(userRepository.findByLogin(any(String.class))).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(User.class))).thenReturn("fake-jwt-token");
        when(jwtService.generateRefreshToken(any(), any(User.class))).thenReturn("fake-refresh-token");

        JwtAuthenticationResponse response = authenticationService.signin(signInRequest);

        assertEquals("fake-jwt-token", response.getToken());
        assertEquals("fake-refresh-token", response.getRefreshToken());
    }

    @Test
    void testRefreshToken() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken("fake-refresh-token");

        User user = new User();
        user.setLogin("test@example.com");

        when(jwtService.extractUserName(any(String.class))).thenReturn("test@example.com");
        when(userRepository.findByLogin(any(String.class))).thenReturn(Optional.of(user));
        when(jwtService.isTokenValid(any(String.class), any(User.class))).thenReturn(true);
        when(jwtService.generateToken(any(User.class))).thenReturn("new-jwt-token");
        when(jwtService.generateRefreshToken(any(), any(User.class))).thenReturn("new-refresh-token"); // Убедитесь, что это значение возвращается

        JwtAuthenticationResponse response = authenticationService.refreshToken(refreshTokenRequest);

        assertEquals("new-jwt-token", response.getToken());
        assertEquals("new-refresh-token", response.getRefreshToken());
    }

}
