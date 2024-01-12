package com.sorawee.fileUpload.auth.service.implement;

import com.sorawee.fileUpload.auth.model.AuthRequest;
import com.sorawee.fileUpload.auth.model.AuthResponse;
import com.sorawee.fileUpload.auth.repository.UserRepository;
import com.sorawee.fileUpload.auth.service.AuthenticationService;
import com.sorawee.fileUpload.auth.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;

    @Override
    public AuthResponse token(AuthRequest request) {

        final UserDetails userDetails =new User(request.getEmail(),"", new ArrayList<>());
        final String token = jwtService.generateToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setToken(token);

        return response;
    }
}
