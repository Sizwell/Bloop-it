package com.itech.security.service;

import com.itech.security.dto.request.SignUpRequest;
import com.itech.security.dto.request.SigninRequest;
import com.itech.security.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
