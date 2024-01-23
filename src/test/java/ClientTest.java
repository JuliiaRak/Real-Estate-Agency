import com.solvd.domain.Client;
import com.solvd.persistence.mybatisImpl.ClientRepositoryMybatisImpl;
import com.solvd.persistence.repositories.ClientRepository;
import com.solvd.service.ClientService;
import com.solvd.service.impl.ClientServiceImpl;

import java.util.Date;

public class ClientTest {
    public static void main(String[] args) {
        Client client = new Client();
        client.setFirstName("Denys");
        client.setLastName("Kulikov");
        client.setEmail("dkulikov@gmail.com");
        client.setPhoneNumber("+111-11-111-11-11");
        client.setRegistrationDate(new Date());

        ClientRepository clientRepository = new ClientRepositoryMybatisImpl();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        clientService.create(client);
        System.out.println(client);
        clientService.deleteById(client.getId());
    }
}
