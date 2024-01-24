import com.solvd.domain.Address;
import com.solvd.persistence.repositories.mybatisImpl.AddressRepositoryMybatisImpl;
import com.solvd.persistence.repositories.AddressRepository;
import com.solvd.service.AddressService;
import com.solvd.service.impl.AddressServiceImpl;

public class AddressTest {
    public static void main(String[] args) {
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

        AddressRepository addressRepository = new AddressRepositoryMybatisImpl();
        AddressService addressService = new AddressServiceImpl(addressRepository);
        addressService.create(address);
        addressService.create(address2);
        System.out.println(addressService.getById(address.getId()));
        System.out.println(addressService.getAll());

        address.setBuilding("3");
        address.setApartment("100");
        addressService.update(address);
        System.out.println(addressService.getById(address.getId()));

        addressService.deleteById(address.getId());
        addressService.deleteById(address2.getId());
    }
}
