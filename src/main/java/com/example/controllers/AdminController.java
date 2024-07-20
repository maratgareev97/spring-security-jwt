package com.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Операции доступны только администраторам")
public class AdminController {

    @GetMapping
    @Operation(summary = "Admin access test", description = "Конечная точка для проверки доступа администратора")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Доступ разрешен");
    }
}
