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

    @PutMapping("/{userId}/{holidayId}")
    public ResponseEntity<String> updateHoliday(@PathVariable Long userId, @PathVariable Long holidayId, @RequestBody HolidayRequest holidayRequest) {
        return holidayService.updateHoliday(userId, holidayId, holidayRequest);
    }

    @GetMapping("/getHolidaysByUserId/{userId}")
    public List<HolidayResponse> getUserHolidaysByUserId(@PathVariable Long userId) {
        return holidayService.getUserHolidaysByUserId(userId);
    }

    @DeleteMapping("/{userId}/{holidayId}")
    public ResponseEntity<String> deleteHoliday(@PathVariable Long userId, @PathVariable Long holidayId) {
        return holidayService.deleteHoliday(userId, holidayId);
    }

}
