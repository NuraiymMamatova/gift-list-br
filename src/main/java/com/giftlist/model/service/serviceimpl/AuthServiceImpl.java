package com.giftlist.model.service.serviceimpl;

import com.giftlist.crypt.Crypto;
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
import org.springframework.beans.factory.annotation.Value;
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

    private final Crypto crypto;

    @Value("${GIFT_LIST_APP_SECRET_KEY}")
    private String GIFT_LIST_APP_SECRET_KEY;

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
        try {
            String result = userRepository.existsByEmail(email) ? emailService.sendSimpleMail(new EmailRequest(email, url + "/" + crypto.encrypt(email, GIFT_LIST_APP_SECRET_KEY), "Gift List аккаунтуңуздун сыр сөзүн унутуп калуудан кийин өзгөртүү.")) : "Мындай email табылган жок!";
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> changePassword(ChangePasswordRequest changePasswordRequest) throws Exception {
        if (!changePasswordRequest.hasPassword() && changePasswordRequest.oldPassword() == null) {
            User user = userRepository.findByEmail(changePasswordRequest.email()).orElseThrow(() -> new Exception("Кирүү үчүн колдонуп жаткан маалыматтар туура эмес!"));
            user.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
            userRepository.save(user);
            return ResponseEntity.ok("Сыр сөзүңүз сакталды!");
        }
        if (changePasswordRequest.oldPassword() != null) {
            User user = userRepository.findByEmail(changePasswordRequest.email()).orElseThrow(() -> new Exception("Кирүү үчүн колдонуп жаткан маалыматтар туура эмес!"));
            if (passwordEncoder.matches(changePasswordRequest.oldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
                userRepository.save(user);
                return ResponseEntity.ok("Сыр сөзүңүз жаңыланды!");
            } else {
                throw new Exception("Киргизген маалымат туура эмес!");
            }
        }
        User user = userRepository.findByEmail(crypto.decrypt(changePasswordRequest.email(), GIFT_LIST_APP_SECRET_KEY)).orElseThrow(() -> new Exception("Кирүү үчүн колдонуп жаткан маалыматтар туура эмес!"));
        user.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Сыр сөзүңүз жаңыланды!");
    }

}
