package com.giftlist.model.service;

import com.giftlist.model.dto.request.HolidayRequest;
import com.giftlist.model.dto.response.HolidayResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HolidayService {
    ResponseEntity<String> saveHoliday(HolidayRequest holidayRequest, String token);

    List<HolidayResponse> getUserHolidaysByUserId(String token);
}
