package com.solvd.service;

import com.solvd.domain.Agreement;
import com.solvd.domain.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AgreementService {
    void create(Agreement agreement, long realEstateId, long clientId) throws EntityNotFoundException;

    void deleteById(long id);

    void update(Agreement agreement) throws EntityNotFoundException;

    Agreement getById(long id) throws EntityNotFoundException;

    List<Agreement> getAll();

    Optional<Agreement> getByClientId(long id) throws EntityNotFoundException;
}
