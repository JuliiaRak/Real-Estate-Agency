package com.solvd;

import com.solvd.domain.Agreement;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.persistence.AgreementRepository;
import com.solvd.persistence.impl.AgreementRepositoryMyBatisImpl;
import com.solvd.service.AgreementService;
import com.solvd.service.impl.AgreementServiceImpl;
import com.solvd.service.impl.ClientServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;


public class AgreementTest {

    private static final AgreementRepository AGREEMENT_REPOSITORY = new AgreementRepositoryMyBatisImpl();
    private static AgreementService agreementService = new AgreementServiceImpl
            (AGREEMENT_REPOSITORY, new RealEstateServiceImpl(), new ClientServiceImpl());


    @Test
    public void createAgreementTest() throws FieldValidationException, EntityNotFoundException {

        Agreement agreement = createSampleAgreement();
        agreementService.create(agreement, 1, 1);
        Assert.assertNotNull(agreement.getId());
        Agreement retrievedAgreement = AGREEMENT_REPOSITORY.findById(agreement.getId()).orElse(null);
        Assert.assertNotNull(retrievedAgreement);
        Assert.assertEquals(agreement, retrievedAgreement);
    }

    @Test
    public void deleteAgreementByIdTest() throws FieldValidationException, EntityNotFoundException {
        Agreement agreement = createSampleAgreement();
        agreementService.create(agreement, 1, 1);

        agreementService.deleteById(agreement.getId());

        Assert.assertFalse(AGREEMENT_REPOSITORY.findById(agreement.getId()).isPresent()); // Перевірте, чи агрімент був видалений
    }

    @Test
    public void updateAgreementTest() throws FieldValidationException, EntityNotFoundException {

        Agreement agreement = agreementService.getById(6);
        agreement.setDuration("24 months");
        agreement.setAmount(new BigDecimal("150000.00"));


        agreementService.update(agreement);


        Agreement updatedAgreement = AGREEMENT_REPOSITORY.findById(agreement.getId()).orElse(null);

        Assert.assertNotNull(updatedAgreement);
        Assert.assertEquals(agreement.getDuration(), updatedAgreement.getDuration());
        Assert.assertEquals(agreement.getAmount(), updatedAgreement.getAmount());
    }

    @Test
    public void getByIdAgreementTest() throws FieldValidationException, EntityNotFoundException {
        Agreement agreement = createSampleAgreement();
        agreementService.create(agreement, 1, 1);

        Agreement agreementFind = agreementService.getById(agreement.getId());
        Assert.assertEquals(agreement, agreementFind);
    }


    private Agreement createSampleAgreement() {
        return new Agreement(0, new Date(), new BigDecimal("100000.00"),
                "12 months", "Active", new RealEstate(), new Client());
    }
}
