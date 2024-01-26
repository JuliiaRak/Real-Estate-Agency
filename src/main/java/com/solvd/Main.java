package com.solvd;

import com.solvd.domain.Address;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
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
    private static final ClientService CLIENT_SERVICE = new ClientServiceImpl();
    private static final AddressService ADDRESS_SERVICE = new AddressServiceImpl();
    private static final RealEstateService REAL_ESTATE_SERVICE = new RealEstateServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Real Estate Agency console app. Please choose an action");
            System.out.println("Enter '0' to register as a new client");
            System.out.println("Enter '1' to login and confirm your identity.");

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
            CLIENT_SERVICE.create(client);
        } catch (PhoneNumberAlreadyExistsException | EmailAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Thank you for registration!");

        try {
            userActions(scanner, client);
        } catch (EntityNotFoundException | LinkAlreadyExistsException e) {
            System.out.println(e.getMessage());
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

            switch (choice) {
                case "1":
                    System.out.print("Please enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Please Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();

                    try {
                        CLIENT_SERVICE.getByEmail(email);
                        Client client = CLIENT_SERVICE.getByPhoneNumber(phoneNumber);

                        System.out.println("Thank you for LogIn");

                        userActions(scanner, client);
                    } catch (EntityNotFoundException | LinkAlreadyExistsException e) {
                        System.out.println(e.getMessage());
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

    public static void userActions(Scanner scanner, Client client) throws EntityNotFoundException, LinkAlreadyExistsException {
        boolean exitLoop = false;
        while (!exitLoop) {
            System.out.println("Now choose an action (write a number):");
            System.out.println("1. Put new real estate up for sale.");
            System.out.println("2. View real estate by type.");
            System.out.println("3. View all real estates.");
            System.out.println("4. View my real estates");
            System.out.println("5. Delete account.");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

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

                    REAL_ESTATE_SERVICE.create(realEstate, client.getId());
                    break;
                case "2":
                    System.out.println("Choose what type of Real Estate you are looking for\n" +
                            "1. Apartament\n" +
                            "2. Building");
                    RealEstateType realEstateType = null;
                    String typeChoice = scanner.nextLine();
                    switch (typeChoice){
                        case "1":
                            realEstateType = RealEstateType.APARTMENT;
                            break;
                        case "2":
                            realEstateType = RealEstateType.BUILDING;
                            break;
                    }
                    try {
                        System.out.println(REAL_ESTATE_SERVICE.getAllByType(realEstateType));
                    } catch (Exception e) {
                        System.out.println("Invalid option. Please Enter '1' or '2' ");
                    }

                case "3":
                    System.out.println(REAL_ESTATE_SERVICE.getAll());
                    // Handle viewing all real estates
                    break;
                case "4":
                    System.out.println(REAL_ESTATE_SERVICE.getAllBySeller(client));
                case "5":
                    System.out.println("Do you really want to delete your account?");
                    System.out.println("Choose an action (write a number):");
                    System.out.println("1. YES");
                    System.out.println("2. Exit");

                    String userInput = scanner.nextLine();

                    switch (userInput) {
                        case "1":
                            break;
                        case "2":
                            exitLoop = true;
                            break;
                        default:
                            break;
                    }
                    break;
                case "6":
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter '1', '2', '3' or '4'.");
            }

            // Add more cases for other actions as needed
        }
    }

    // Define other methods for handling real estate actions, employee management, agreements, etc.
}