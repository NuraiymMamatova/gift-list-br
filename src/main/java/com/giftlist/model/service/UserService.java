package com.giftlist.model.service;

import com.giftlist.model.dto.request.UserRequest;
import com.giftlist.model.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserResponse updateProfile(String token, UserRequest userRequest);

    UserResponse getProfile(String token);
}
