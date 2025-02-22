package com.giftlist.model.dto.response;

import com.giftlist.model.enums.ClothingSize;
import com.giftlist.model.enums.ShoeSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String country;
    private LocalDate dateOfBirth;
    private String facebookLink;
    private String hobbies;
    private String importantToKnow;
    private String instagramLink;
    private String phoneNumber;
    private String image;
    private String telegramLink;
    private String vkLink;
    private String fullName;
    private String email;
    private ClothingSize clothingSize;
    private ShoeSize shoeSize;
}
