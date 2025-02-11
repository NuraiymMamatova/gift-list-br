package com.giftlist.model.service.serviceimpl;

import com.giftlist.model.dto.request.RegistrationRequest;
import com.giftlist.model.dto.response.AuthenticationResponse;
import com.giftlist.model.entities.User;
import com.giftlist.model.enums.Role;
import com.giftlist.model.repository.UserRepository;
import com.giftlist.model.service.AuthService;
import com.giftlist.model.service.JwtService;
import lombok.RequiredArgsConstructor;
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

    public AuthenticationResponse register(RegistrationRequest request) {
        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRepository.save(user);
        String token = jwtService.generateJwtToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(RegistrationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()
                )
        );
        User user = userRepository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.generateJwtToken(user);
        return new AuthenticationResponse(token);
    }

}

