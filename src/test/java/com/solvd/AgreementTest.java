package com.solvd;

import com.solvd.domain.Agreement;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.persistence.AgreementRepository;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.persistence.impl.AgreementRepositoryMyBatisImpl;
import com.solvd.persistence.impl.RealEstateRepositoryMybatisImpl;
import com.solvd.service.AgreementService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.AgreementServiceImpl;
import com.solvd.service.impl.ClientServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

public class AgreementTest {
    private static final Logger LOGGER = LogManager.getLogger(AgreementTest.class);

    private final AgreementRepository agreementRepository = new AgreementRepositoryMyBatisImpl();
    private final RealEstateRepository realEstateRepository = new RealEstateRepositoryMybatisImpl();
    private final AgreementService agreementService = new AgreementServiceImpl
            (agreementRepository, new RealEstateServiceImpl(), new ClientServiceImpl());
    private final RealEstateService realEstateService = new RealEstateServiceImpl();

    @Test
    public void createAgreementTest() throws FieldValidationException, EntityNotFoundException {
        Agreement agreement = createSimpleAgreement();
        agreementService.create(agreement, 1, 2);
        Agreement retrievedAgreement = agreementRepository.findById(agreement.getId()).orElse(null);

        Assertions.assertNotNull(agreement.getId());
        Assertions.assertNotNull(retrievedAgreement);
        Assertions.assertEquals(agreement, retrievedAgreement);
        agreementService.deleteById(agreement.getId());
        setRealEstateAvailable();
    }

    @Test
    public void deleteAgreementByIdTest() throws FieldValidationException, EntityNotFoundException {
        Agreement agreement = createSimpleAgreement();
        agreementService.create(agreement, 1, 2);
        agreementService.deleteById(agreement.getId());

        Assertions.assertFalse(agreementRepository.findById(agreement.getId()).isPresent());
        setRealEstateAvailable();
    }

    @Test
    public void updateAgreementTest() throws FieldValidationException, EntityNotFoundException {
        Agreement agreement = createSimpleAgreement();
        agreementService.create(agreement, 1, 2);
        agreement.setDuration("24 months");
        agreement.setAmount(new BigDecimal("150000.00"));
        agreementService.update(agreement);
        Agreement updatedAgreement = agreementRepository.findById(agreement.getId()).orElse(null);

        Assertions.assertNotNull(updatedAgreement);
        Assertions.assertEquals(agreement.getDuration(), updatedAgreement.getDuration());
        Assertions.assertEquals(agreement.getAmount(), updatedAgreement.getAmount());
        agreementService.deleteById(agreement.getId());
        setRealEstateAvailable();
    }

    @Test
    public void getByIdAgreementTest() throws FieldValidationException, EntityNotFoundException {
        Agreement agreement = createSimpleAgreement();
        agreementService.create(agreement, 1, 2);
        Agreement agreementFind = agreementService.getById(agreement.getId());

        Assertions.assertEquals(agreement, agreementFind);
        agreementService.deleteById(agreement.getId());
        setRealEstateAvailable();
    }

    private Agreement createSimpleAgreement() {
        return new Agreement(0, new Date(), new BigDecimal("100000.00"),
                "12 months", "Active", new RealEstate(), new Client());
    }

    private void setRealEstateAvailable() {
        RealEstate realEstate = null;
        try {
            realEstate = realEstateRepository.findById(1L).get();
            realEstate.setAvailable(true);
            realEstateService.update(realEstate);
        } catch (EntityNotFoundException | FieldValidationException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}