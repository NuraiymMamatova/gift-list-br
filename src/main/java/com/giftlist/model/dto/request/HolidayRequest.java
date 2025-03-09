package com.giftlist.model.dto.request;

import java.time.LocalDate;

public record HolidayRequest(
        String holidayName,
        LocalDate holidayDate,
        String holidayImageUrl
) {
}
