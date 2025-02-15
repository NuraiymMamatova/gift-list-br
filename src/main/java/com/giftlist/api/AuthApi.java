package com.giftlist.api;

import com.giftlist.model.dto.request.LoginRequest;
import com.giftlist.model.dto.request.RegistrationRequest;
import com.giftlist.model.dto.response.AuthenticationResponse;
import com.giftlist.model.service.AuthService;
import com.giftlist.smtp.EmailDetails;
import com.giftlist.smtp.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthApi {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(authService.register(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details) {
        return emailService.sendMailWithAttachment(details);
    }

}
