package com.giftlist.api;

import com.giftlist.model.dto.request.ChangePasswordRequest;
import com.giftlist.model.dto.request.LoginRequest;
import com.giftlist.model.dto.request.RegistrationRequest;
import com.giftlist.model.dto.request.ViaGoogleRequest;
import com.giftlist.model.dto.response.AuthenticationResponse;
import com.giftlist.model.service.AuthService;
import com.giftlist.smtp.EmailRequest;
import com.giftlist.smtp.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

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

    @PostMapping("/viaGoogle")
    public ResponseEntity<AuthenticationResponse> authViaGoogle(@RequestBody ViaGoogleRequest request) {
        return ResponseEntity.ok(authService.viaGoogle(request));
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailRequest details) {
        return emailService.sendSimpleMail(details);
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailRequest details) {
        return emailService.sendMailWithAttachment(details);
    }

    @PostMapping("/sendHTMLEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        Context context = new Context();
        // Set variables for the template from the POST request data
        context.setVariable("name", emailRequest.getName());
        context.setVariable("message", emailRequest.getMsgBody());
        context.setVariable("subject", emailRequest.getSubject());
        try {
        return     emailService.sendHTMLEmail(emailRequest.getRecipient(), emailRequest.getSubject(), "testmailtemplate", context);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error sending email: " + e.getMessage());
        }
    }

    @PostMapping("/forgotPasswordSendURLtoEmail")
    public ResponseEntity<String> forgotPasswordSendURLtoEmail(@RequestParam(name = "email") String email, @RequestParam(name = "url") String url) {
       return authService.forgotPasswordSendURLtoEmail(email, url);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        return authService.changePassword(changePasswordRequest);
    }

}
