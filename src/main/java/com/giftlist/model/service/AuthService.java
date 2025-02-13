package com.giftlist.model.service;

import com.giftlist.model.dto.request.RegistrationRequest;
import com.giftlist.model.dto.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse register(RegistrationRequest request);

    AuthenticationResponse authenticate(RegistrationRequest request);
}
