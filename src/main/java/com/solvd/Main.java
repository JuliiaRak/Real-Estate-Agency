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
        boolean exitLoop = false;

        while (!exitLoop) {
            System.out.println("Welcome to the Real Estate Agency console app. Please choose an action");
            System.out.println("Enter '0' to register as a new client");
            System.out.println("Enter '1' to login and confirm your identity.");
            System.out.println("Enter '2' to Exit.");

            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    registerClient(scanner);
                    break;
                case "1":
                    login(scanner);
                    break;
                case "2":
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter '0', '1' or '2'.");
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
                    System.out.println("Invalid option. Please enter '1' or '2'");
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
            System.out.println("5. Order real estate");
            System.out.println("6. Delete account.");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Please enter real estate address details:");
                    System.out.print("1. Enter country: ");
                    String country = scanner.nextLine();
                    System.out.print("2. Enter region: ");
                    String region = scanner.nextLine();
                    System.out.print("3. Enter city: ");
                    String city = scanner.nextLine();
                    System.out.print("4. Enter street: ");
                    String street = scanner.nextLine();
                    System.out.print("5. Enter building: ");
                    String building = scanner.nextLine();
                    System.out.print("6. Enter apartment: ");
                    String apartment = scanner.nextLine();

                    Address address = new Address();
                    address.setCountry(country);
                    address.setRegion(region);
                    address.setCity(city);
                    address.setStreet(street);
                    address.setBuilding(building);
                    address.setApartment(apartment);

                    RealEstate realEstate = new RealEstate();

                    System.out.println("Please enter real estate details:");
                    System.out.print("1. Enter price: ");
                    String price = scanner.nextLine();
                    System.out.print("2. Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("3. Enter Real Estate Type: ");
                    System.out.println("Enter(1 or 2). Choose type of Real Estate \n" +
                            "1. Apartament\n" +
                            "2. Building");
                    String apartmentType = scanner.nextLine();
                    switch (apartmentType) {
                        case "1":
                            realEstate.setRealEstateType(RealEstateType.APARTMENT);
                            break;
                        case "2":
                            realEstate.setRealEstateType(RealEstateType.BUILDING);
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                    if(realEstate.getRealEstateType() == null) {
                        break;
                    }

                    System.out.print("4. Enter real estate metrics: ");
                    String metrics = scanner.nextLine();
                    System.out.print("5. Enter rooms: ");
                    String rooms = scanner.nextLine();

                    try {
                        realEstate.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
                        realEstate.setAvailable(true);
                        realEstate.setDescription(description);
                        realEstate.setMetrics(metrics);
                        realEstate.setRooms(Integer.parseInt(rooms));
                        realEstate.setSeller(client);
                        realEstate.setAddress(address);
                        REAL_ESTATE_SERVICE.create(realEstate, client.getId());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Choose(1 or 2) what type of Real Estate you are looking for\n" +
                            "1. Apartament\n" +
                            "2. Building");
                    RealEstateType realEstateType;
                    String typeChoice = scanner.nextLine();
                    switch (typeChoice){
                        case "1":
                            realEstateType = RealEstateType.APARTMENT;
                            System.out.println(REAL_ESTATE_SERVICE.getAllByType(realEstateType));
                            break;
                        case "2":
                            realEstateType = RealEstateType.BUILDING;
                            System.out.println(REAL_ESTATE_SERVICE.getAllByType(realEstateType));
                            break;
                        default:
                            System.out.println("Invalid option. Please Enter '1' or '2' ");
                    }
                    break;
                case "3":
                    System.out.println(REAL_ESTATE_SERVICE.getAll());
                    break;
                case "4":
                    System.out.println(REAL_ESTATE_SERVICE.getAllBySeller(client));
                    break;
                case "5":

                case "6":
                    System.out.println("Do you really want to delete your account?");
                    System.out.println("Choose an action (write a number):");
                    System.out.println("1. YES");
                    System.out.println("2. Exit");

                    String userInput = scanner.nextLine();

                    switch (userInput) {
                        case "1":
                            CLIENT_SERVICE.deleteById(client.getId());
                            break;
                        case "2":
                            exitLoop = true;
                            break;
                        default:
                            break;
                    }
                    break;
                case "7":
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