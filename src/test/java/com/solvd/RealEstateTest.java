package com.solvd;

import com.solvd.domain.Address;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.service.AddressService;
import com.solvd.service.ClientService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.AddressServiceImpl;
import com.solvd.service.impl.ClientServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RealEstateTest {
    private RealEstateService realEstateService = new RealEstateServiceImpl();
    private ClientService clientService = new ClientServiceImpl();
    private AddressService addressService = new AddressServiceImpl();

    @Test
    public void createRealEstateTest() throws EntityNotFoundException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException {
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

        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");

        RealEstate realEstate = new RealEstate();
        realEstate.setPrice(BigDecimal.valueOf(100000));
        realEstate.setAvailable(true);
        realEstate.setDescription("New apartmens");
        realEstate.setRealEstateType(RealEstateType.APARTMENT);
        realEstate.setMetrics("24");
        realEstate.setRooms(2);
        realEstate.setAddress(address);

        clientService.create(client);
        realEstate.setSeller(client);

        realEstateService.create(realEstate, client.getId());
        RealEstate createdRealEstate = realEstateService.getAvailableById(realEstate.getId());

        Assertions.assertNotNull(createdRealEstate);
        Assertions.assertEquals(realEstate, createdRealEstate);

        realEstateService.deleteById(realEstate.getId());
        clientService.deleteById(client.getId());
        addressService.deleteById(createdRealEstate.getAddress().getId());
    }

    @Test
    public void updateRealEstateTest() throws EntityNotFoundException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException {
        Client.Builder builder = new Client.Builder();
        builder.setFirstName("John");
        builder.setLastName("Doe");
        builder.setEmail("jdoe@gmail.com");
        builder.setPhoneNumber("+380991234567");
        builder.setRegistrationDate(new Date());
        Client client = builder.build();
        clientService.create(client);

        RealEstate realEstate = new RealEstate();
        realEstate.setPrice(BigDecimal.valueOf(120000));
        realEstate.setAvailable(true);
        realEstate.setDescription("Updated apartment");
        realEstate.setRealEstateType(RealEstateType.BUILDING);
        realEstate.setMetrics("40");
        realEstate.setRooms(3);
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("western region");
        address.setCity("Lviv");
        address.setStreet("Lviv street");
        address.setBuilding("5");
        address.setApartment("55");
        realEstate.setAddress(address);
        realEstate.setSeller(client);

        realEstateService.create(realEstate, client.getId());

        realEstate.setPrice(BigDecimal.valueOf(150000));
        realEstate.setDescription("New house with garden");
        realEstate.setRealEstateType(RealEstateType.APARTMENT);
        realEstate.setMetrics("50");
        realEstate.setRooms(4);

        realEstateService.update(realEstate);

        RealEstate updatedRealEstate = realEstateService.getAvailableById(realEstate.getId());

        Assertions.assertNotNull(updatedRealEstate);
        Assertions.assertEquals(realEstate, updatedRealEstate);

        realEstateService.deleteById(realEstate.getId());
        clientService.deleteById(client.getId());
        addressService.deleteById(updatedRealEstate.getAddress().getId());
    }

    @Test
    public void getAllAvailableRealEstatesTest() throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException, FieldValidationException {
        Client.Builder builder = new Client.Builder();
        builder.setFirstName("Alice");
        builder.setLastName("Smith");
        builder.setEmail("asmith@gmail.com");
        builder.setPhoneNumber("+380991234567");
        builder.setRegistrationDate(new Date());
        Client client1 = builder.build();
        clientService.create(client1);

        RealEstate realEstate1 = new RealEstate();
        realEstate1.setPrice(BigDecimal.valueOf(80000));
        realEstate1.setAvailable(true);
        realEstate1.setDescription("Cozy apartment");
        realEstate1.setRealEstateType(RealEstateType.APARTMENT);
        realEstate1.setMetrics("30");
        realEstate1.setRooms(1);

        Address address1 = new Address();
        address1.setCountry("Ukraine");
        address1.setRegion("southern region");
        address1.setCity("Odessa");
        address1.setStreet("Sea street");
        address1.setBuilding("10");
        address1.setApartment("15");
        realEstate1.setAddress(address1);
        realEstate1.setSeller(client1);

        realEstateService.create(realEstate1, client1.getId());

        builder.setFirstName("Bob");
        builder.setLastName("Johnson");
        builder.setEmail("bjohnson@gmail.com");
        builder.setPhoneNumber("+380991234522");
        builder.setRegistrationDate(new Date());
        Client client2 = builder.build();
        clientService.create(client2);

        RealEstate realEstate2 = new RealEstate();
        realEstate2.setPrice(BigDecimal.valueOf(95000));
        realEstate2.setAvailable(true);
        realEstate2.setDescription("Spacious house");
        realEstate2.setRealEstateType(RealEstateType.APARTMENT);
        realEstate2.setMetrics("120");
        realEstate2.setRooms(5);
        Address address2 = new Address();
        address2.setCountry("Ukraine");
        address2.setRegion("northern region");
        address2.setCity("Kharkiv");
        address2.setStreet("Green street");
        address2.setBuilding("25");
        address2.setApartment("N/A");
        realEstate2.setAddress(address2);
        realEstate2.setSeller(client2);

        realEstateService.create(realEstate2, client2.getId());

        List<RealEstate> availableRealEstates = realEstateService.getAll();

        Assertions.assertEquals(2, availableRealEstates.size());

        realEstateService.deleteById(realEstate1.getId());
        realEstateService.deleteById(realEstate2.getId());
        clientService.deleteById(client1.getId());
        clientService.deleteById(client2.getId());
        addressService.deleteById(realEstate1.getAddress().getId());
        addressService.deleteById(realEstate2.getAddress().getId());
    }

    @Test
    public void deleteAddressTest() throws FieldValidationException, EntityNotFoundException, EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
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

        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");

        RealEstate realEstate = new RealEstate();
        realEstate.setPrice(BigDecimal.valueOf(100000));
        realEstate.setAvailable(true);
        realEstate.setDescription("New apartmens");
        realEstate.setRealEstateType(RealEstateType.APARTMENT);
        realEstate.setMetrics("24");
        realEstate.setRooms(2);
        realEstate.setAddress(address);

        clientService.create(client);
        realEstate.setSeller(client);

        realEstateService.create(realEstate, client.getId());
        RealEstate createdRealEstate = realEstateService.getAvailableById(realEstate.getId());

        realEstateService.deleteById(realEstate.getId());
        clientService.deleteById(client.getId());
        addressService.deleteById(createdRealEstate.getAddress().getId());

        try {
            Assertions.assertNull(realEstateService.getAvailableById(createdRealEstate.getId()));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
