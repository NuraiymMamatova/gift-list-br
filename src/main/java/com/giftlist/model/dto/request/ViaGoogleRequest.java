package com.giftlist.model.dto.request;

public record ViaGoogleRequest(
        String email,
        String fullName,
        String picture
) {
}
