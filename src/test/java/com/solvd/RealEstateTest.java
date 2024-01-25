package com.solvd;

import com.solvd.domain.Address;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EmailAlreadyExistException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistException;
import com.solvd.persistence.AddressRepository;
import com.solvd.persistence.ClientRepository;
import com.solvd.persistence.RealEstateRepository;
import com.solvd.persistence.impl.AddressRepositoryMybatisImpl;
import com.solvd.persistence.impl.ClientRepositoryMybatisImpl;
import com.solvd.persistence.impl.RealEstateRepositoryMybatisImpl;
import com.solvd.service.AddressService;
import com.solvd.service.ClientService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.AddressServiceImpl;
import com.solvd.service.impl.ClientServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;

import java.math.BigDecimal;
import java.util.Date;

public class RealEstateTest {
    public static void main(String[] args) throws PhoneNumberAlreadyExistException, EmailAlreadyExistException {
        Client.Builder builder = new Client.Builder();
        try {
            builder.setFirstName("Denys");
            builder.setLastName("Kulikov");
            builder.setEmail("dkulikov@gmail.com");
            builder.setPhoneNumber("+380991234567");
            builder.setRegistrationDate(new Date());
        } catch (IllegalArgumentException e) {
            System.out.println("An invalid object was created: " + e.getMessage());
        }
        Client client = builder.build();

        Address address = new Address(0, "Ukraine", "central region", "Kyiv",
                "Kyiv street", "2", "99");

        RealEstate realEstate = new RealEstate();
        realEstate.setPrice(BigDecimal.valueOf(100000));
        realEstate.setAvailable(true);
        realEstate.setDescription("New apartmens");
        realEstate.setRealEstateType(RealEstateType.APARTMENT);
        realEstate.setMetrics("24 square meters");
        realEstate.setRooms(2);

        ClientRepository clientRepository = new ClientRepositoryMybatisImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        clientService.create(client);

        System.out.println(clientRepository.findById(client.getId()));

        AddressRepository addressRepository = new AddressRepositoryMybatisImpl();
        AddressService addressService = new AddressServiceImpl(addressRepository);

        RealEstateRepository realEstateRepository = new RealEstateRepositoryMybatisImpl();
        RealEstateService realEstateService = new RealEstateServiceImpl(realEstateRepository, addressService);
        realEstate.setSeller(client);
        realEstate.setAddress(address);
        realEstateService.create(realEstate, client.getId());

        System.out.println(addressService.getById(address.getId()));
        address.setBuilding("3");
        address.setApartment("100");
        addressService.update(address);

        client.setEmail("dkulikov@ukr.net");
        client.setPhoneNumber("+333-33-111-11-11");
        clientService.update(client);

        realEstate.setMetrics("100 square meters");
        realEstate.setRooms(3);
        realEstateService.update(realEstate);
        System.out.println(realEstateService.getById(realEstate.getId()));
        System.out.println(realEstateService.getAll());

        realEstateService.deleteById(realEstate.getId());
        addressService.deleteById(address.getId());
        clientService.deleteById(client.getId());
    }
}
