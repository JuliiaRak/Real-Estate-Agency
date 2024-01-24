import com.solvd.domain.Address;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.persistence.repositories.mybatisImpl.AddressRepositoryMybatisImpl;
import com.solvd.persistence.repositories.mybatisImpl.ClientRepositoryMybatisImpl;
import com.solvd.persistence.repositories.mybatisImpl.RealEstateRepositoryMybatisImpl;
import com.solvd.persistence.repositories.AddressRepository;
import com.solvd.persistence.repositories.ClientRepository;
import com.solvd.persistence.repositories.RealEstateRepository;
import com.solvd.service.AddressService;
import com.solvd.service.ClientService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.AddressServiceImpl;
import com.solvd.service.impl.ClientServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;

import java.math.BigDecimal;
import java.util.Date;

public class RealEstateTest {
    public static void main(String[] args) {
        Client client = new Client();
        Client client2 = new Client();
        client.setFirstName("Denys");
        client.setLastName("Kulikov");
        client.setEmail("dkulikov@gmail.com");
        client.setPhoneNumber("+111-11-111-11-11");
        client.setRegistrationDate(new Date());

        client2.setFirstName("Ivan");
        client2.setLastName("Kulikov");
        client2.setEmail("ikulikov@gmail.com");
        client2.setPhoneNumber("+222-22-222-22-22");
        client2.setRegistrationDate(new Date());

        Address address = new Address();
        Address address2 = new Address();
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
        realEstate.setMetrics("24 square meters");
        realEstate.setRooms(2);

        ClientRepository clientRepository = new ClientRepositoryMybatisImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        clientService.create(client);
        System.out.println(client);

        AddressRepository addressRepository = new AddressRepositoryMybatisImpl();
        AddressService addressService = new AddressServiceImpl(addressRepository);
        addressService.create(address);
        System.out.println(address);

        RealEstateRepository realEstateRepository = new RealEstateRepositoryMybatisImpl();
        RealEstateService realEstateService = new RealEstateServiceImpl(realEstateRepository);
        realEstateService.create(realEstate, client.getId(), address.getId());
        System.out.println(realEstateService.getById(realEstate.getId()));
        System.out.println(realEstateService.getAll());

        realEstateService.deleteById(realEstate.getId());
        addressService.deleteById(address.getId());
        addressService.deleteById(address2.getId());
        clientService.deleteById(client.getId());
        clientService.deleteById(client2.getId());
    }
}
