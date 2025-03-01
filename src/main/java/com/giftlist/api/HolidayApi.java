package com.giftlist.api;

import com.giftlist.model.dto.request.HolidayRequest;
import com.giftlist.model.dto.response.HolidayResponse;
import com.giftlist.model.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/holidays")
@PreAuthorize("hasAuthority('USER')")
public class HolidayApi {

    private final HolidayService holidayService;

    @PostMapping
    public String saveHoliday(@RequestHeader("Authorization") String token, @RequestBody HolidayRequest holidayRequest) {
        return holidayService.saveHoliday(holidayRequest, token);
    }

    @GetMapping
    public List<HolidayResponse> getUserHolidaysByUserId(@RequestHeader("Authorization") String token) {
        return holidayService.getUserHolidaysByUserId(token);
    }

}
