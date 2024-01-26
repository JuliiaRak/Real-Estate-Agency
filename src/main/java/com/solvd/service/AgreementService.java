package com.solvd.service;

import com.solvd.domain.Agreement;
import com.solvd.domain.exceptions.EntityNotFoundException;

import java.util.List;

public interface AgreementService {
    void create(Agreement agreement, long realEstateId, long clientId);
    void deleteById(long id) throws EntityNotFoundException;

    void update(Agreement agreement);

    Agreement getById(long id) throws EntityNotFoundException;

    List<Agreement> getAll() throws EntityNotFoundException;
}