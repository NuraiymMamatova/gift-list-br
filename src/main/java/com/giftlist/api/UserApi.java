package com.giftlist.api;

import com.giftlist.model.dto.request.UserRequest;
import com.giftlist.model.dto.response.UserResponse;
import com.giftlist.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserApi {

    private final UserService userService;

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public UserResponse updateProfile(@RequestHeader("Authorization") String token, @RequestBody UserRequest userRequest) {
        return userService.updateProfile(token.substring(7), userRequest);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public UserResponse getProfile(@RequestHeader("Authorization") String token) {
        return userService.getProfile(token.substring(7));
    }

}
