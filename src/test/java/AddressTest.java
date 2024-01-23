import com.solvd.domain.Address;
import com.solvd.persistence.mybatisImpl.AddressRepositoryMybatisImpl;
import com.solvd.persistence.repositories.AddressRepository;
import com.solvd.service.AddressService;
import com.solvd.service.impl.AddressServiceImpl;

public class AddressTest {
    public static void main(String[] args) {
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setRegion("central region");
        address.setCity("Kyiv");
        address.setStreet("Kyiv street");
        address.setBuilding("2");
        address.setApartment("99");

        AddressRepository addressRepository = new AddressRepositoryMybatisImpl();
        AddressService addressService = new AddressServiceImpl(addressRepository);
        addressService.create(address);
        System.out.println(address);
        addressService.deleteById(address.getId());
    }
}
