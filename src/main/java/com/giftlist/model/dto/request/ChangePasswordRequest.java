package com.giftlist.model.dto.request;

public record ChangePasswordRequest(
        String oldPassword, String newPassword, String email, boolean hasPassword
) {
}
