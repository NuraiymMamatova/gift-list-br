package com.giftlist.api;

import com.giftlist.model.dto.request.HolidayRequest;
import com.giftlist.model.dto.response.HolidayResponse;
import com.giftlist.model.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/holidays")
@PreAuthorize("hasAuthority('USER')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class HolidayApi {

    private final HolidayService holidayService;

    @PostMapping
    public ResponseEntity<String> saveHoliday(@RequestHeader("Authorization") String token, @RequestBody HolidayRequest holidayRequest) {
        return holidayService.saveHoliday(holidayRequest, token.substring(7));
    }

    @GetMapping
    public List<HolidayResponse> getUserHolidaysByUserId(@RequestHeader("Authorization") String token) {
        return holidayService.getUserHolidaysByUserId(token.substring(7));
    }

}
