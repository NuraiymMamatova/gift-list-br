package com.giftlist.model.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}