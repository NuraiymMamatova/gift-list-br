package com.giftlist.model.service.serviceimpl;

import com.giftlist.model.dto.request.UserRequest;
import com.giftlist.model.dto.response.UserResponse;
import com.giftlist.model.entities.User;
import com.giftlist.model.repository.UserRepository;
import com.giftlist.model.service.JwtService;
import com.giftlist.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public UserResponse updateProfile(String token, UserRequest userRequest) {
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        user.setFullName(userRequest.fullName());
        user.setClothingSize(userRequest.clothingSize());
        user.setCountry(userRequest.country());
        user.setDateOfBirth(userRequest.dateOfBirth());
        user.setFacebookLink(userRequest.facebookLink());
        user.setVkLink(userRequest.vkLink());
        user.setTelegramLink(userRequest.telegramLink());
        user.setShoeSize(userRequest.shoeSize());
        user.setPhoneNumber(String.valueOf(userRequest.phoneNumber()));
        user.setInstagramLink(userRequest.instagramLink());
        user.setImportantToKnow(userRequest.importantToKnow());
        user.setImage(userRequest.image());
        user.setHobbies(userRequest.hobbies());
        userRepository.save(user);
        return userRepository.findByUserId(user.getUserId());
    }

    @Override
    public UserResponse getProfile(String token) {
        String username = jwtService.extractUsername(token);
        return userRepository.getUserProfileByEMail(username);
    }

}
