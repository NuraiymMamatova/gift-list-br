package com.giftlist.model.repository;

import com.giftlist.model.dto.response.UserResponse;
import com.giftlist.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("select case when count(u.email) > 0 then true else false end from User u where u.email = :email")
    boolean existsByEmail(String email);

    @Query("select new com.giftlist.model.dto.response.UserResponse(u.country, u.dateOfBirth, u.facebookLink, u.hobbies, u.importantToKnow, u.instagramLink, u.phoneNumber, u.image, u.telegramLink, u.vkLink, u.fullName, u.email, u.clothingSize, u.shoeSize) from User u where u.userId = :userId")
    UserResponse findByUserId(Long userId);

}