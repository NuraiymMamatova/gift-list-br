package com.giftlist.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.giftlist.model.enums.ClothingSize;
import com.giftlist.model.enums.Country;
import com.giftlist.model.enums.ShoeSize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record UserRequest(
        @Enumerated(EnumType.STRING)
        Country country,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth,
        String facebookLink,
        String hobbies,
        String importantToKnow,
        String instagramLink,
        String phoneNumber,
        String image,
        String telegramLink,
        String vkLink,
        String fullName,
        @Enumerated(EnumType.STRING)
        ClothingSize clothingSize,
        @Enumerated(EnumType.STRING)
        ShoeSize shoeSize) {
}
