package com.solvd;

import com.solvd.domain.Agreement;
import com.solvd.persistence.AddressRepository;
import com.solvd.persistence.AgreementRepository;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.persistence.impl.AddressRepositoryMybatisImpl;
import com.solvd.persistence.impl.AgreementRepositoryMyBatisImpl;
import com.solvd.persistence.impl.RealEstateRepositoryMybatisImpl;
import com.solvd.service.AddressService;
import com.solvd.service.AgreementService;
import com.solvd.service.impl.AddressServiceImpl;
import com.solvd.service.impl.AgreementServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;
import org.apache.logging.log4j.LogManager;

public class Main {
    public static void main(String[] args) {

        AgreementRepository agreementRepository =  new AgreementRepositoryMyBatisImpl();
        RealEstateRepository realEstateRepository =  new RealEstateRepositoryMybatisImpl();
        AddressRepository addressRepository =  new AddressRepositoryMybatisImpl();

        AddressService addressService =  new AddressServiceImpl(addressRepository);
        RealEstateServiceImpl realEstateService =  new RealEstateServiceImpl(realEstateRepository, addressService);

        AgreementService agreementService =  new AgreementServiceImpl(agreementRepository, realEstateService);

    }
}
