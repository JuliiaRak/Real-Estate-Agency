package com.solvd.persistence;

import com.solvd.domain.Agreement;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface AgreementRepository {
    void create(@Param("agreement") Agreement agreement, @Param("realEstateId") long realEstateId, @Param("clientId") long clientId);

    void deleteById(long id);

    void update(Agreement agreement);

    Optional<Agreement> findById(long id);

    List<Agreement> findAll();
}
