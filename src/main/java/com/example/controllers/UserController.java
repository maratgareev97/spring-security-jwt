package com.example.controllers;

import com.example.models.User;
import com.example.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "Операции, связанные с управлением пользователями")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Say Hello", description = "Для тестирования API")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("проверка");
    }
}
