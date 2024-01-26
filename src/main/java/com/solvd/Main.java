package com.solvd;

import com.solvd.domain.Address;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EmailAlreadyExistException;
import com.solvd.domain.exceptions.EntityNotFoundException;
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
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Real Estate Agency console app.");
            System.out.println("Write '0' to register as a new client or '1' to login and confirm your identity.");

            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    registerClient(scanner);
                    break;
                case "1":
                    login(scanner);
                    break;
                default:
                    System.out.println("Invalid option. Please enter '0' or '1'.");
            }
        }
    }

    private static void registerClient(Scanner scanner) {
        System.out.println("Please enter your registration details:");
        System.out.print("1. Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("2. Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("3. Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("4. Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        Client.Builder builder = new Client.Builder();
        builder.setFirstName(firstName);
        builder.setLastName(lastName);
        builder.setEmail(email);
        builder.setPhoneNumber(phoneNumber);
        builder.setRegistrationDate(new Date());

        Client client = builder.build();
        try {
            ClientRepository clientRepository = new ClientRepositoryMybatisImpl();
            ClientService clientService = new ClientServiceImpl(clientRepository);
            clientService.create(client);
        } catch (PhoneNumberAlreadyExistException | EmailAlreadyExistException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Thank you for registration!");

        boolean exitLoop = false;

        while (!exitLoop) {
            System.out.println("Now choose an action (write a number):");
            System.out.println("1. Put new real estate up for sale.");
            System.out.println("2. View real estate by type.");
            System.out.println("3. View all real estates.");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            AddressRepository addressRepository = new AddressRepositoryMybatisImpl();
            AddressService addressService = new AddressServiceImpl(addressRepository);

            RealEstateRepository realEstateRepository = new RealEstateRepositoryMybatisImpl();
            RealEstateService realEstateService = new RealEstateServiceImpl(realEstateRepository, addressService);

            switch (choice) {
                case "1":
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
                    realEstate.setMetrics("24 square meters");
                    realEstate.setRooms(2);
                    realEstate.setSeller(client);
                    realEstate.setAddress(address);

                    realEstateService.create(realEstate, client.getId());
                    break;
                case "2":
                    // Handle viewing real estate by type
                    break;
                case "3":
                    System.out.println(realEstateService.getAll());
                    // Handle viewing all real estates
                    break;
                case "4":
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter '1', '2', or '3'.");
            }

            // Add more cases for other actions as needed
        }
    }

    private static void login(Scanner scanner) {
        boolean exitLoop = false;

        while (!exitLoop) {
            System.out.println("Now choose an action (write a number):");
            System.out.println("1. Enter your email and phone number.");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            ClientRepository clientRepository = new ClientRepositoryMybatisImpl();
            ClientService clientService = new ClientServiceImpl(clientRepository);

            switch (choice) {
                case "1":
                    System.out.print("Please enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Please Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();

                    try {
                        clientService.getByEmail(email);
                        Client client = clientService.getByPhoneNumber(phoneNumber);

                        System.out.println("Thank you for LogIn");

                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case "2":
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter '1', '2'");
            }
        }
    }

    // Define other methods for handling real estate actions, employee management, agreements, etc.
}
