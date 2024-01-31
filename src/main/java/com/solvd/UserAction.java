package com.solvd;

import com.solvd.domain.Agreement;
import com.solvd.domain.Client;
import com.solvd.domain.Employee;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EmailAlreadyExistsException;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.domain.exceptions.PhoneNumberAlreadyExistsException;
import com.solvd.service.AgreementService;
import com.solvd.service.ClientService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.AgreementServiceImpl;
import com.solvd.service.impl.ClientServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserAction {
    private static final ClientService CLIENT_SERVICE = new ClientServiceImpl();
    private static final RealEstateService REAL_ESTATE_SERVICE = new RealEstateServiceImpl();
    private static final AgreementService AGREEMENT_SERVICE = new AgreementServiceImpl();

    public static void registerClient(Scanner scanner) {
        System.out.println("Please enter your registration details:");
        System.out.print("1. Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("2. Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("3. Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("4. Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        Client client = Client.builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setRegistrationDate(new Date())
                .build();

        try {
            CLIENT_SERVICE.create(client);
        } catch (FieldValidationException | PhoneNumberAlreadyExistsException | EmailAlreadyExistsException e) {
            System.out.println("\n" + e.getMessage());
            System.out.println("Please try again.");
            return;
        }

        System.out.println("\n" + "Thank you for registration!");

        try {
            userActions(scanner, client);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void login(Scanner scanner) {
        Client client = new Client();

        System.out.println("Please enter your logIn details:");
        System.out.print("Please enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Please enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        try {
            Client clientByEmail = CLIENT_SERVICE.getByEmail(email);
            Client clientByPhone = CLIENT_SERVICE.getByPhoneNumber(phoneNumber);
            if (clientByEmail.getId() == clientByPhone.getId()) {
                client = clientByEmail;
            } else {
                System.out.println("Email and phone don`t match");
                return;
            }

        } catch (EntityNotFoundException e) {
            System.out.println("\n" + e.getMessage());
            System.out.println("Please try again.");
            return;
        }

        System.out.println("\n" + "You've successfully Logged In. Hello " + client.getFirstName() + "!");

        try {
            userActions(scanner, client);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void userActions(Scanner scanner, Client client) throws EntityNotFoundException {
        boolean exitLoop = false;
        while (!exitLoop) {
            System.out.println("\nNow choose an action:");
            System.out.println("1. Put new real estate up for sale.");
            System.out.println("2. View real estate by type.");
            System.out.println("3. View all real estates.");
            System.out.println("4. View my real estates");
            System.out.println("5. Order real estate");
            System.out.println("6. Delete account.");
            System.out.println("7. View my agreements.");
            System.out.println("8. View my meetings.");
            System.out.println("9. Pay for agreement.");
            System.out.println("10. Settings.");
            System.out.println("11. Exit.");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    RealEstateAction.createRealEstate(scanner, client);
                    break;
                case "2":
                    RealEstateAction.viewRealEstateByType(scanner);
                    break;
                case "3":
                    RealEstateAction.viewAllRealEstates();
                    break;
                case "4":
                    List<RealEstate> allRealEstates = REAL_ESTATE_SERVICE.getAllBySeller(client);
                    if (allRealEstates.isEmpty()) {
                        System.out.println("You do not have real estates");
                        break;
                    }
                    System.out.println(RealEstate.getTableHeader());
                    for (RealEstate realEstate : allRealEstates) {
                        System.out.println(realEstate);
                    }
                    break;
                case "5":
                    System.out.println("You want to create a meet to view Real Estate or you ready to buy?\n" +
                            "1. Create a meeting\n" +
                            "2. Create a order");
                    System.out.print("Enter your choice: ");

                    String choose = scanner.nextLine();
                    try {
                        switch (choose) {
                            case "1":
                                Employee employee = EmployeeAction.chooseEmployee(scanner);
                                MeetingAction.createMeeting(scanner, client, employee);
                                break;
                            case "2":
                                RealEstateAction.orderRealEstate(scanner, client);
                                break;
                        }
                    } catch (EntityNotFoundException | FieldValidationException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "6":
                    if(deleteAccount(scanner, client)) {
                        return;
                    }
                    break;
                case "7":
                    Optional<Agreement> agreement = AGREEMENT_SERVICE.getByClientId(client.getId());
                    if (agreement.isPresent()) {
                        System.out.println(Agreement.getTableHeader());
                        System.out.println(agreement.get());
                        System.out.println();
                    } else {
                        System.out.println("You have no agreements yet");
                    }
                    break;
                case "8":
                    try {
                        MeetingAction.viewClientsMeetings(scanner, client);
                    } catch (EntityNotFoundException | FieldValidationException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "9":
                    try {
                        PaymentAction.payForAgreement(client);
                    } catch (EntityNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "10":
                    settings(scanner, client);
                    break;
                case "11":
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static boolean deleteAccount(Scanner scanner, Client client) {
        System.out.println("Do you really want to delete your account?");
        System.out.println("Choose an action (enter 1 or 2):");
        System.out.println("1. YES");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");

        String userInput = scanner.nextLine();

        switch (userInput) {
            case "1":
                CLIENT_SERVICE.deleteById(client.getId());
                return true;
            case "2":
                return false;
            default:
                System.out.println("Invalid option.");
                break;
        }
        return false;
    }

    public static void settings(Scanner scanner, Client client) {
        System.out.println("SETTINGS\n" +
                "1. Change phone  number\n" +
                "2. Change email");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Input your new phone number");
                String phoneNumber = scanner.nextLine();
                client.setPhoneNumber(phoneNumber);
                try {
                    CLIENT_SERVICE.update(client);
                    Optional<Agreement> agreement = AGREEMENT_SERVICE.getByClientId(client.getId());
                    if (agreement.isPresent()) {
                        agreement.get().setClient(client);
                        AGREEMENT_SERVICE.update(agreement.get());
                    }
                } catch (FieldValidationException | EntityNotFoundException | EmailAlreadyExistsException |
                         PhoneNumberAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "2":
                System.out.println("Input your new email");
                String email = scanner.nextLine();
                client.setEmail(email);
                try {
                    CLIENT_SERVICE.update(client);
                } catch (FieldValidationException | EntityNotFoundException | EmailAlreadyExistsException |
                         PhoneNumberAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
    }
}
