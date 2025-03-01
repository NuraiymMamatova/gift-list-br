package com.giftlist.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "holidays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Holiday {
    @SequenceGenerator(name = "holiday_id", sequenceName = "holiday_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holiday_id")
    @Column(name = "holiday_id")
    @Id
    private Long holidayId;

    private String holidayName;

    private LocalDate holidayDate;

    private String holidayImageUrl;

    public Holiday(String holidayName, LocalDate holidayDate, String holidayImageUrl) {
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
        this.holidayImageUrl = holidayImageUrl;
    }
}
