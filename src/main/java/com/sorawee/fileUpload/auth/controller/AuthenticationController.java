package com.sorawee.fileUpload.auth.controller;

import com.sorawee.fileUpload.auth.model.AuthRequest;
import com.sorawee.fileUpload.auth.model.AuthResponse;
import com.sorawee.fileUpload.auth.service.AuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/token")
    public ResponseEntity<AuthResponse> token(@RequestBody AuthRequest request) {

        return ResponseEntity.ok(authenticationService.token(request));
    }

}