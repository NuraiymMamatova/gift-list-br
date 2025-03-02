package com.giftlist.model.service.serviceimpl;

import com.giftlist.model.dto.request.HolidayRequest;
import com.giftlist.model.dto.response.HolidayResponse;
import com.giftlist.model.entities.Holiday;
import com.giftlist.model.entities.User;
import com.giftlist.model.repository.HolidayRepository;
import com.giftlist.model.repository.UserRepository;
import com.giftlist.model.service.HolidayService;
import com.giftlist.model.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Override
    public ResponseEntity<String> saveHoliday(HolidayRequest holidayRequest, String token) {
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        user.addHoliday(holidayRepository.save(new Holiday(holidayRequest.holidayName(), holidayRequest.holidayDate(), holidayRequest.holidayImageUrl())));
        userRepository.save(user);
        return ResponseEntity.ok("Successfully saved holiday");
    }

    @Override
    public List<HolidayResponse> getUserHolidaysByUserId(String token) {
        return List.of();
    }
}
