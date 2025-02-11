package com.giftlist.api;

import com.giftlist.model.dto.request.RegistrationRequest;
import com.giftlist.model.dto.response.AuthenticationResponse;
import com.giftlist.model.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(authService.register(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(authService.authenticate(registrationRequest));
    }

}
