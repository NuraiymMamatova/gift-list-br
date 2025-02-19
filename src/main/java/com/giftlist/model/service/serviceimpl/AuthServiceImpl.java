package com.giftlist.model.service.serviceimpl;

import com.giftlist.model.dto.request.ChangePasswordRequest;
import com.giftlist.model.dto.request.LoginRequest;
import com.giftlist.model.dto.request.RegistrationRequest;
import com.giftlist.model.dto.request.ViaGoogleRequest;
import com.giftlist.model.dto.response.AuthenticationResponse;
import com.giftlist.model.entities.User;
import com.giftlist.model.enums.Role;
import com.giftlist.model.repository.UserRepository;
import com.giftlist.model.service.AuthService;
import com.giftlist.model.service.JwtService;
import com.giftlist.smtp.EmailRequest;
import com.giftlist.smtp.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;

    public AuthenticationResponse register(RegistrationRequest request) {
        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRepository.save(user);
        String token = jwtService.generateJwtToken(user);
        return new AuthenticationResponse(user.getEmail(), token, user.getUserId(), user.getFullName(), user.getImage(), user.getRole().name());
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()
                )
        );
        User user = userRepository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.generateJwtToken(user);
        return new AuthenticationResponse(user.getEmail(), token, user.getUserId(), user.getFullName(), user.getImage(), user.getRole().name());
    }

    @Override
    public AuthenticationResponse viaGoogle(ViaGoogleRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseGet(() -> userRepository.save(new User(request.fullName(), request.email(), request.picture(), Role.USER)));
        String token = jwtService.generateJwtToken(user);
        return new AuthenticationResponse(user.getEmail(), token, user.getUserId(), user.getFullName(), user.getImage(), user.getRole().name());
    }

    @Override
    public ResponseEntity<String> forgotPasswordSendURLtoEmail(String email, String url) {
        String result = userRepository.existsByEmail(email) ? emailService.sendSimpleMail(new EmailRequest(email, url, "Gift List аккаунтуңуздун сыр сөзүн унутуп калуудан кийин өзгөртүү.")) : "Мындай email табылган жок!";
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<String> changePassword(ChangePasswordRequest changePasswordRequest) throws Exception {
        User user = userRepository.findByEmail(changePasswordRequest.email()).orElseThrow(() -> new Exception("Кирүү үчүн колдонуп жаткан маалыматтар туура эмес!"));
        if (user.getPassword().matches(changePasswordRequest.oldPassword())) {
            user.setPassword(changePasswordRequest.newPassword());
            userRepository.save(user);
        } else {
            throw new Exception("Кирүү үчүн колдонуп жаткан маалыматтар туура эмес!");
        }
        return ResponseEntity.ok("Сыр сөзүңүз жаңыланды!");
    }

}
