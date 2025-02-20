package com.giftlist.model.service;

import com.giftlist.model.dto.request.ChangePasswordRequest;
import com.giftlist.model.dto.request.LoginRequest;
import com.giftlist.model.dto.request.RegistrationRequest;
import com.giftlist.model.dto.request.ViaGoogleRequest;
import com.giftlist.model.dto.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthenticationResponse register(RegistrationRequest request);

    AuthenticationResponse authenticate(LoginRequest request);

    AuthenticationResponse viaGoogle(ViaGoogleRequest request);

    ResponseEntity<String> forgotPasswordSendURLtoEmail(String email, String url);

    ResponseEntity<String> changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;
}
