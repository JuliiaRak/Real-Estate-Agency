package com.solvd.persistence;

import com.solvd.domain.Meeting;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository {
    void create(@Param("meeting") Meeting meeting, @Param("realEstateId") Long realEstateId, @Param("buyerId") Long buyerId, @Param("employeeId") Long employeeId);

    void deleteById(long id);

    void update(@Param("meeting") Meeting meeting, @Param("realEstateId") Long realEstateId, @Param("buyerId") Long buyerId, @Param("employeeId") Long employeeId);

    Optional<Meeting> findById(long id);

    List<Meeting> findAll();
}
