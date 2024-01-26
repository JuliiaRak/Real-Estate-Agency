package com.solvd.service.impl;

import com.solvd.domain.Agreement;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.AgreementRepository;
import com.solvd.persistence.impl.AgreementRepositoryMyBatisImpl;
import com.solvd.service.AgreementService;
import com.solvd.service.RealEstateService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository = new AgreementRepositoryMyBatisImpl();
    private final RealEstateService realEstateService = new RealEstateServiceImpl();

    @Override
    public void create(Agreement agreement, long realEstateId, long clientId) {
        if (checkStatus(agreement.getStatus()) && (agreement.getRealEstate() != null) && (agreement.getDuration() != null)
                && (agreement.getClient() != null) && (checkAmount(agreement.getAmount())) && (agreement.getDate() != null)) {
            agreementRepository.create(agreement, realEstateId, clientId);
            if (agreement.getDuration().contains("Indefinite")) {
                realEstateService.deleteById(realEstateId);
            }

        } else {
            throw new IllegalArgumentException("Wrong parameters in agreements");
        }
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        Optional<Agreement> exitingAgreement = agreementRepository.findById(id);
        if (exitingAgreement.isPresent()) {
            agreementRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("agreement", id);
        }
    }

    @Override
    public void update(Agreement agreement) {
        if (checkStatus(agreement.getStatus()) && (agreement.getRealEstate() != null) && (agreement.getDuration() != null)
                && (agreement.getClient() != null) && (checkAmount(agreement.getAmount())) && (agreement.getDate() != null)) {
            agreementRepository.update(agreement);
        }
    }

    @Override
    public Agreement getById(long id) throws EntityNotFoundException {
        return agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("agreement", id));
    }

    @Override
    public List<Agreement> getAll() {
        return agreementRepository.findAll();

    }

    private boolean checkStatus(String status) {
        if (status != null && (!status.isEmpty()) && (status.contains("paid") | status.contains("unpaid"))) {
            return true;
        } else return false;
    }

    private boolean checkDuration(String duration) {
        if (duration != null && (!duration.isEmpty()) && (duration.endsWith("months") || duration.contains("Indefinite"))) {
            return true;
        } else return false;
    }

    private boolean checkAmount(BigDecimal amount) {
        BigDecimal lowerLimit = BigDecimal.valueOf(100);
        BigDecimal highLimit = BigDecimal.valueOf(1000000);

        if (amount != null
                && (amount.compareTo(lowerLimit) > 0)
                && (amount.compareTo(highLimit) < 0)) {
            return true;
        } else return false;
    }
}
