package com.solvd.service.impl;

import com.solvd.domain.Agreement;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.AgreementRepository;
import com.solvd.persistence.impl.AgreementRepositoryMyBatisImpl;
import com.solvd.service.AgreementService;
import com.solvd.service.RealEstateService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.bigint.MaxLongValidator;
import com.solvd.service.validators.bigint.MinLongValidator;
import com.solvd.service.validators.object.NotNullObjectValidator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import com.solvd.service.validators.string.SizeStringValidator;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final RealEstateService realEstateService;

    public AgreementServiceImpl() {
        this.agreementRepository = new AgreementRepositoryMyBatisImpl();
        this.realEstateService = new RealEstateServiceImpl();
    }

    @Override
    public void create(Agreement agreement, long realEstateId, long clientId) {
        validate(agreement);

        agreementRepository.create(agreement, realEstateId, clientId);

        if (agreement.getDuration().contains("Indefinite")) {
            realEstateService.deleteById(realEstateId);
        }
    }

    private void validate(Agreement agreement) {
        Validator<Object> notNullValidator = new NotNullObjectValidator();
        notNullValidator.validate("agreement", agreement);
        notNullValidator.validate("agreement amount", agreement.getAmount());
        notNullValidator.validate("agreement date", agreement.getDate());

        Validator<String> stringValidator = new SizeStringValidator(new NotEmptyStringValidator(new NotNullStringValidator()));
        stringValidator.validate("agreement status", agreement.getStatus());
        stringValidator.validate("agreement duration", agreement.getDuration());

        Validator<Long> longValidator = new MaxLongValidator(new MinLongValidator());
        longValidator.validate("agreement amount", agreement.getAmount().longValue());
    }

    @Override
    public void deleteById(long id) {
        agreementRepository.deleteById(id);
    }


    @Override
    public void update(Agreement agreement) {
        validate(agreement);
        agreementRepository.update(agreement);
    }

    @Override
    public Agreement getById(long id) throws EntityNotFoundException {
        return agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("agreement", id));
    }

    @Override
    public List<Agreement> getAll(){
        return agreementRepository.findAll();
    }
}
