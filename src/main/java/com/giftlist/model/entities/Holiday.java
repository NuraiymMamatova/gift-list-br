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

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    public Holiday(String holidayName, LocalDate holidayDate, String holidayImageUrl, User owner) {
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
        this.holidayImageUrl = holidayImageUrl;
        this.owner = owner;
    }

    public Holiday(String holidayName, LocalDate holidayDate, String holidayImageUrl) {
        if (holidayName != null) this.holidayName = holidayName;
        if (holidayDate != null) this.holidayDate = holidayDate;
        if (holidayImageUrl != null) this.holidayImageUrl = holidayImageUrl;
    }
}
