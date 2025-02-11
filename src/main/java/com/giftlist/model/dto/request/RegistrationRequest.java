package com.giftlist.model.dto.request;

public record RegistrationRequest(
        String fullName,
        String email,
        String password
) {
}
