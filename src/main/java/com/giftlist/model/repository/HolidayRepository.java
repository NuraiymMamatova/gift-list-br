package com.giftlist.model.repository;

import com.giftlist.model.dto.response.HolidayResponse;
import com.giftlist.model.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query("select new com.giftlist.model.dto.response.HolidayResponse(h.holidayId, h.holidayName, h.holidayDate, h.holidayImageUrl) from Holiday h where h.owner.userId = :userId")
    List<HolidayResponse> getAllUserHolidaysByUserId(Long userId);
}