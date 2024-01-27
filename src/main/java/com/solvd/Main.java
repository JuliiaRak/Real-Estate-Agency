package com.solvd;

import com.solvd.domain.*;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.LinkAlreadyExistsException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.service.*;
import com.solvd.service.impl.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final ClientService CLIENT_SERVICE = new ClientServiceImpl();
    private static final AddressService ADDRESS_SERVICE = new AddressServiceImpl();
    private static final RealEstateService REAL_ESTATE_SERVICE = new RealEstateServiceImpl();
    private static final  AgreementService AGREEMENT_SERVICE =  new AgreementServiceImpl();
    private static final MeetingService MEETING_SERVICE =  new MeetingServiceImpl();
    private static final EmployeeService EMPLOYEE_SERVICE =  new EmployeeServiceImpl();

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
        } catch (IllegalArgumentException | NullPointerException |
                 PhoneNumberAlreadyExistsException | EmailAlreadyExistsException e) {
            System.out.println("\n" + e.getMessage());
            return;
        }

        System.out.println("\n" + "Thank you for registration!");

        try {
            userActions(scanner, client);
        } catch (IllegalArgumentException | NullPointerException |
                 EntityNotFoundException | LinkAlreadyExistsException e) {
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
                    } catch (IllegalArgumentException | NullPointerException |
                             EntityNotFoundException | LinkAlreadyExistsException e) {
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
            System.out.println("7. View my ordered.");
            System.out.println("8. Pay for agreement.");
            System.out.println("9. Settings");
            System.out.println("10. Exit");
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
                    } catch (IllegalArgumentException | NullPointerException | EntityNotFoundException e) {
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
                            System.out.println("Invalid option.");
                    }
                    break;
                case "3":
                    List<RealEstate> realEstates =  REAL_ESTATE_SERVICE.getAll();
                    for(RealEstate item : realEstates){
                        System.out.println(item +"\n");
                    }
                    break;
                case "4":
                    System.out.println(REAL_ESTATE_SERVICE.getAllBySeller(client));
                    break;
                case "5":
                    System.out.println("You want to create a meet to view Real Estate or you ready to buy?\n" +
                            "1. Create a meeting\n" +
                            "2. Create a order");
                    String choose = scanner.nextLine();
                    switch (choose) {
                        case "1":
                            createMeeting(scanner, client, chooseEmployee(scanner));
                            break;
                        case "2":
                            try {
                                orderRealEstate(scanner, client);
                            } catch (IllegalArgumentException | NullPointerException | EntityNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                    }
                    break;
                case "6":
                    System.out.println("Do you really want to delete your account?");
                    System.out.println("Choose an action (write a number):");
                    System.out.println("1. YES");
                    System.out.println("2. Exit");

                    String userInput = scanner.nextLine();

                    switch (userInput) {
                        case "1":
                            CLIENT_SERVICE.deleteById(client.getId());
                            exitLoop = true;
                            break;
                        case "2":
                            exitLoop = true;
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                    break;
                case "7":
                    System.out.println(AGREEMENT_SERVICE.getByClientId(client.getId()));
                    break;
                case "8":
                    try {
                        payForAgreement(client);
                    } catch (IllegalArgumentException | NullPointerException | EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "9":
                    settings(scanner, client);
                    break;
                case "10":
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }

            // Add more cases for other actions as needed
        }
    }

    private static void payForAgreement(Client client) throws EntityNotFoundException {
        Optional<Agreement> agreement = AGREEMENT_SERVICE.getByClientId(client.getId());
        if(agreement.isEmpty()) {
            System.out.println("You cannot have more than one Real Estate AGREEMENT");
            return;
        }

        System.out.println("Thank you for paying for agreement");
        RealEstate realEstate = agreement.get().getRealEstate();
        realEstate.setAvailable(false);
        REAL_ESTATE_SERVICE.update(realEstate);

        AGREEMENT_SERVICE.deleteById(agreement.get().getId());
    }

    public static void orderRealEstate(Scanner scanner, Client client) throws EntityNotFoundException {
        if(AGREEMENT_SERVICE.getByClientId(client.getId()).isPresent()) {
            System.out.println("You cannot have more than one Real Estate AGREEMENT");
            return;
        }

        System.out.println("Enter the id of Real Estate you want to buy");
        String choice = scanner.nextLine();

        RealEstate realEstate;
        realEstate = REAL_ESTATE_SERVICE.getById(Long.parseLong(choice));

        System.out.println("The price of Real Estate " + realEstate.getPrice());

        Agreement agreement = new Agreement();
        agreement.setRealEstate(realEstate);
        agreement.setDate(new Date());
        agreement.setDuration("3 months");
        agreement.setAmount(realEstate.getPrice());
        agreement.setClient(client);
        agreement.setStatus("unpaid");

        AGREEMENT_SERVICE.create(agreement, realEstate.getId(), client.getId());

        System.out.println("Your agreement is ready ");
        System.out.println(agreement);
    }

    public static void settings(Scanner scanner, Client client){
        System.out.println("SETTINGS\n" +
                            "1. Change phone  number\n" +
                            "2. Change email");
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                System.out.println("Input your new phone number");
                String phoneNumber = scanner.nextLine();
                client.setPhoneNumber(phoneNumber);
                try {
                    CLIENT_SERVICE.update(client);
                        Optional<Agreement> agreement = AGREEMENT_SERVICE.getByClientId(client.getId());
                        if (agreement.isPresent()){
                            agreement.get().setClient(client);
                            AGREEMENT_SERVICE.update(agreement.get());
                        }
                } catch (EntityNotFoundException | EmailAlreadyExistsException | PhoneNumberAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "2":
                System.out.println("Input your new email");
                String email = scanner.nextLine();
                client.setEmail(email);
                try {
                    CLIENT_SERVICE.update(client);
                } catch (EntityNotFoundException | EmailAlreadyExistsException | PhoneNumberAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }


    }

    public static void createMeeting(Scanner scanner, Client client, Employee employee){
        Meeting meeting =  new Meeting();
        RealEstate realEstate = null;
        System.out.println("Input what date you want make a view");
        String dateString = scanner.nextLine();
        System.out.println("Enter the id of Real Estate you want to view");
        String realEstateString = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = null;
        try {
            utilDate = dateFormat.parse(dateString);
            realEstate = REAL_ESTATE_SERVICE.getById(Long.parseLong(realEstateString));
        } catch (ParseException | EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        meeting.setMeetingDateTime(date);
        meeting.setInquiryDate(new Date());
        meeting.setMeetingStatus("Pending");
        meeting.setBuyer(client);
        meeting.setRealEstate(realEstate);
        meeting.setEmployee(employee);

        System.out.println("Your meeting will be at " + date + " with " + employee.getFirstName() + " " + employee.getLastName());
    }
    public static Employee chooseEmployee(Scanner scanner) {
        Employee employee = null;
        try {
            employee = EMPLOYEE_SERVICE.getById(1);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

        // Define other methods for handling real estate actions, employee management, agreements, etc.
}