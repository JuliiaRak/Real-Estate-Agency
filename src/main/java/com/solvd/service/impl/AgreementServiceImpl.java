package com.solvd.service.impl;

import com.solvd.domain.Agreement;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.persistence.AgreementRepository;
import com.solvd.persistence.impl.AgreementRepositoryMyBatisImpl;
import com.solvd.service.AgreementService;
import com.solvd.service.ClientService;
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
import java.util.Optional;

@AllArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final RealEstateService realEstateService;
    private final ClientService clientService;

    public AgreementServiceImpl() {
        this.agreementRepository = new AgreementRepositoryMyBatisImpl();
        this.realEstateService = new RealEstateServiceImpl();
        this.clientService = new ClientServiceImpl();
    }

    @Override
    public void create(Agreement agreement, long realEstateId, long clientId) throws EntityNotFoundException {
        validate(agreement);
        checkRealEstate(realEstateId);
        checkClient(clientId);

        agreementRepository.create(agreement, realEstateId, clientId);

        realEstateService.hideById(realEstateId);
    }

    private void checkRealEstate(long realEstateId) throws EntityNotFoundException {
        if (!realEstateService.existsById(realEstateId)) {
            throw new EntityNotFoundException("Real estate", realEstateId);
        }
    }

    private void checkClient(long clientId) throws EntityNotFoundException {
        if (!clientService.existsById(clientId)) {
            throw new EntityNotFoundException("Client", clientId);
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
    public void update(Agreement agreement) throws EntityNotFoundException {
        if (agreementRepository.findById(agreement.getId()).isEmpty()) {
            throw new EntityNotFoundException("Agreement", agreement.getId());
        }
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

    @Override
    public Optional<Agreement> getByClientId(long clientId) {
        return getAll().stream()
                .filter(agreement -> agreement.getClient().getId() == clientId)
                .findFirst();

    }
}