package com.solvd;

import com.solvd.domain.Address;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.AddressService;
import com.solvd.service.impl.AddressServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressTest {
    private static final Logger LOGGER = LogManager.getLogger(AddressTest.class);

    private final AddressService addressService = new AddressServiceImpl();

    @Test
    public void createAddressTest() throws EntityNotFoundException, FieldValidationException {
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");
        addressService.create(address);
        Address createdAddress = addressService.getById(address.getId());

        Assertions.assertNotNull(createdAddress);
        Assertions.assertEquals(address, createdAddress);
        addressService.deleteById(address.getId());
    }

    @Test
    public void getAddressByIdTest() throws EntityNotFoundException, FieldValidationException {
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");
        addressService.create(address);
        Address retrievedAddress = addressService.getById(address.getId());

        Assertions.assertEquals(address, retrievedAddress);
        addressService.deleteById(address.getId());
    }

    @Test
    public void getAllAddressesTest() throws FieldValidationException {
        Address address = new Address();
        Address address2 = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");

        address2.setCountry("Ukraine");
        address2.setRegion("central region");
        address2.setCity("Kyiv");
        address2.setStreet("Lviv street");
        address2.setBuilding("3");
        address2.setApartment("12");
        addressService.create(address);
        addressService.create(address2);
        List<Address> allAddresses = addressService.getAll();

        Assertions.assertTrue(allAddresses.contains(address));
        Assertions.assertTrue(allAddresses.contains(address2));
        addressService.deleteById(address.getId());
        addressService.deleteById(address2.getId());
    }

    @Test
    public void updateAddressTest() throws EntityNotFoundException, FieldValidationException {
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");
        addressService.create(address);

        address.setBuilding("3");
        address.setApartment("100");
        addressService.update(address);
        Address updatedAddress = addressService.getById(address.getId());

        Assertions.assertEquals("3", updatedAddress.getBuilding());
        Assertions.assertEquals("100", updatedAddress.getApartment());
        addressService.deleteById(address.getId());
    }

    @Test
    public void deleteAddressTest() throws FieldValidationException {
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");
        addressService.create(address);
        addressService.deleteById(address.getId());
        try {
            Assertions.assertNull(addressService.getById(address.getId()));
        } catch (EntityNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
