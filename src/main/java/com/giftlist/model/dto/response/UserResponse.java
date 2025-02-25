package com.giftlist.model.dto.response;

import com.giftlist.model.enums.ClothingSize;
import com.giftlist.model.enums.Country;
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
    private Country country;
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
    private boolean hasPassword;

    public UserResponse(Country country, LocalDate dateOfBirth, String facebookLink, String hobbies, String importantToKnow, String instagramLink, String phoneNumber, String image, String telegramLink, String vkLink, String fullName, String email, ClothingSize clothingSize, ShoeSize shoeSize) {
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.facebookLink = facebookLink;
        this.hobbies = hobbies;
        this.importantToKnow = importantToKnow;
        this.instagramLink = instagramLink;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.telegramLink = telegramLink;
        this.vkLink = vkLink;
        this.fullName = fullName;
        this.email = email;
        this.clothingSize = clothingSize;
        this.shoeSize = shoeSize;
    }
}
