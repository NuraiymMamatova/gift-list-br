package com.giftlist.model.entities;

import com.giftlist.model.enums.ClothingSize;
import com.giftlist.model.enums.Country;
import com.giftlist.model.enums.Role;
import com.giftlist.model.enums.ShoeSize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @SequenceGenerator(name = "user_id", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
    @Column(name = "user_id")
    @Id
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private String image;

    @Column(name = "is_agree")
    private boolean isAgree;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "important_to_know")
    private String importantToKnow;

    @Column(name = "instagram_link")
    private String instagramLink;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "telegram_link")
    private String telegramLink;

    @Column(name = "vk_link")
    private String vkLink;

    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(name = "clothing_size")
    @Enumerated(EnumType.STRING)
    private ClothingSize clothingSize;

    @Column(name = "shoe_size")
    @Enumerated(EnumType.STRING)
    private ShoeSize shoeSize;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String fullName, String email, String image, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.image = image;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
