package com.solvd;

import com.solvd.domain.Address;
import com.solvd.domain.Agreement;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.persistence.AddressRepository;
import com.solvd.persistence.AgreementRepository;
import com.solvd.persistence.ClientRepository;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.persistence.impl.AddressRepositoryMybatisImpl;
import com.solvd.persistence.impl.AgreementRepositoryMyBatisImpl;
import com.solvd.persistence.impl.ClientRepositoryMybatisImpl;
import com.solvd.persistence.impl.RealEstateRepositoryMybatisImpl;
import com.solvd.service.AddressService;
import com.solvd.service.AgreementService;
import com.solvd.service.ClientService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.AddressServiceImpl;
import com.solvd.service.impl.AgreementServiceImpl;
import com.solvd.service.impl.ClientServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;

import java.math.BigDecimal;
import java.util.Date;


public class AgreementTest {
    public static void main(String[] args) throws EntityNotFoundException, LinkAlreadyExistsException, PhoneNumberAlreadyExistsException, EmailAlreadyExistsException {
        //creating instances
        Client client = new Client();
        Client seller = new Client();
        client.setEmail("00aj20121890231129@gmail.com");
        client.setPhoneNumber("0851000012");
        client.setEmail("aj2619@gmail.com");
        client.setPhoneNumber("008082");
        client.setFirstName("Anna");
        client.setLastName("Polichuk");
        client.setRegistrationDate(new Date());

        seller.setFirstName("Ivan");
        seller.setLastName("Kulikov");
        seller.setEmail("ikd0004233124003v@gmail.com");
        seller.setPhoneNumber("1202-1252342");
        seller.setEmail("ikd46778ev@gmail.com");
        seller.setPhoneNumber("+232-2542");
        seller.setRegistrationDate(new Date());

        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");

        RealEstate realEstate = new RealEstate();
        realEstate.setRealEstateType(RealEstateType.APARTMENT);
        realEstate.setPrice(BigDecimal.valueOf(156000));
        realEstate.setMetrics("3245");
        realEstate.setDescription("Very cool apartment");
        realEstate.setRooms(5);
        realEstate.setAvailable(true);
        realEstate.setAddress(address);
        realEstate.setSeller(seller);


        Agreement agreement = new Agreement();
        Agreement agreement2 =  new Agreement();
        agreement.setAmount(BigDecimal.valueOf(37218));
        agreement.setDate(new Date());
        agreement.setClient(client);
        agreement.setStatus("Paid");
        agreement.setRealEstate(realEstate);
        agreement.setDuration("Indefinite");

        agreement2.setAmount(BigDecimal.valueOf(500));
        agreement2.setDate(new Date(2023-11-02));
        agreement2.setClient(client);
        agreement2.setStatus("unpaid");
        agreement2.setRealEstate(realEstate);
        agreement2.setDuration("3 months");
        agreement.setDuration("3 month");

        //creating repositories, services

        ClientRepository clientRepository = new ClientRepositoryMybatisImpl();
        ClientService clientService = new ClientServiceImpl();
        clientService.create(client);
        clientService.create(seller);
        try {
            clientService.create(client);
            clientService.create(seller);
        } catch (EmailAlreadyExistsException | PhoneNumberAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        System.out.println(client);

        AddressRepository addressRepository = new AddressRepositoryMybatisImpl();
        AddressService addressService = new AddressServiceImpl();
        addressService.create(address);

        System.out.println(address);

        RealEstateRepository realEstateRepository = new RealEstateRepositoryMybatisImpl();
        RealEstateService realEstateService = new RealEstateServiceImpl();
        realEstateService.create(realEstate, client.getId());
        //System.out.println(realEstate);

        AgreementRepository agreementRepository = new AgreementRepositoryMyBatisImpl();
        AgreementService agreementService = new AgreementServiceImpl();
        //check CRUD operations

        /*agreement.setDuration("4 months");
        agreementService.update(agreement);

        System.out.println(agreementService.getById(5));

        System.out.println(realEstate);

        AgreementRepository agreementRepository = new AgreementRepositoryMyBatisImpl();
        AgreementService agreementService = new AgreementServiceImpl(agreementRepository);
        agreementService.create(agreement, realEstate.getId(), client.getId());
        System.out.println(agreement);

        //check CRUD operations

        agreement.setDuration("4 months");
        agreementService.update(agreement);

        System.out.println(agreementService.getById(5));

        agreementService.deleteById(1);
        agreementService.deleteById(2);
        agreementService.deleteById(3);
        agreementService.deleteById(4);

        List<Agreement> agreements = agreementService.getAll();
        for (Agreement agrm : agreements) {
            System.out.println(agrm);
        }*/

        //check the validation
        agreementService.create(agreement2, realEstate.getId(), client.getId());
        System.out.println(realEstateService.getById(realEstate.getId()));

        System.out.println(agreement2);

        //List<Agreement> agreements = agreementService.getAll();
        //for (Agreement agrm : agreements) {
         //   System.out.println(agrm);
       // }
    }

}
