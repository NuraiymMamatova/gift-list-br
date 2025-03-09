package com.giftlist.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HolidayResponse {

    private Long holidayId;

    private String holidayName;

    private LocalDate holidayDate;

    private String holidayImageUrl;
}
