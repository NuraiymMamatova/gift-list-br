package com.giftlist.model.service;

import com.giftlist.model.dto.request.LoginRequest;
import com.giftlist.model.dto.request.RegistrationRequest;
import com.giftlist.model.dto.request.ViaGoogleRequest;
import com.giftlist.model.dto.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse register(RegistrationRequest request);

    AuthenticationResponse authenticate(LoginRequest request);

    AuthenticationResponse viaGoogle(ViaGoogleRequest request);
}
