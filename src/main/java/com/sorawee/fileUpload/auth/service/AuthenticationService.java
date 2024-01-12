package com.sorawee.fileUpload.auth.service;

import com.sorawee.fileUpload.auth.model.AuthRequest;
import com.sorawee.fileUpload.auth.model.AuthResponse;

public interface AuthenticationService {
    AuthResponse token(AuthRequest request);
}
