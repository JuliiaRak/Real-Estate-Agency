package com.solvd.service;

import com.solvd.domain.Agreement;

import java.util.List;
import java.util.Optional;

public interface AgreementService {
    void create(Agreement agreement, long realEstateId, long clientId);

    void deleteById(long id);

    void update(Agreement agreement);

    Agreement getById(long id);

    List<Agreement> getAll();
}
