package com.solvd;

import com.solvd.domain.Address;
import com.solvd.domain.Agreement;
import com.solvd.domain.Client;
import com.solvd.domain.RealEstate;
import com.solvd.domain.enums.RealEstateType;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.AgreementService;
import com.solvd.service.RealEstateService;
import com.solvd.service.impl.AgreementServiceImpl;
import com.solvd.service.impl.RealEstateServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RealEstateAction {
    private static final RealEstateService REAL_ESTATE_SERVICE = new RealEstateServiceImpl();
    private static final AgreementService AGREEMENT_SERVICE = new AgreementServiceImpl();
    public static void createRealEstate(Scanner scanner, Client client) {
        Address address = new Address();
        RealEstate realEstate = new RealEstate();

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

        address.setCountry(country);
        address.setRegion(region);
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);
        address.setApartment(apartment);

        System.out.println("Please enter real estate details:");
        System.out.print("1. Enter price: ");
        String price = scanner.nextLine();
        System.out.print("2. Enter description: ");
        String description = scanner.nextLine();

        boolean badType = true;
        do {
            System.out.print("3. Enter Real Estate Type: ");
            System.out.println("Choose type of Real Estate. Enter 1 or 2.\n" +
                    "\t1. Apartament\n" +
                    "\t2. Building");
            System.out.print("\tEnter your choice:");
            String apartmentType = scanner.nextLine();
            switch (apartmentType) {
                case "1":
                    realEstate.setRealEstateType(RealEstateType.APARTMENT);
                    badType = false;
                    break;
                case "2":
                    realEstate.setRealEstateType(RealEstateType.BUILDING);
                    badType = false;
                    break;
                default:
                    System.out.println("Invalid real estate type. Try again");
            }
        } while (badType);

        System.out.print("4. Enter real estate metrics: ");
        String metrics = scanner.nextLine();
        System.out.print("5. Enter rooms: ");
        String rooms = scanner.nextLine();

        try {
            realEstate.setPrice(parseDouble(price));
            realEstate.setAvailable(true);
            realEstate.setDescription(description);
            realEstate.setMetrics(metrics);
            realEstate.setRooms(parseInt(rooms));
            realEstate.setSeller(client);
            realEstate.setAddress(address);
            REAL_ESTATE_SERVICE.create(realEstate, client.getId());
            System.out.println("\n" + "Thanks! You've successfully created a real estate! Here it is:");
            System.out.println(RealEstate.getTableHeader());
            System.out.println(realEstate);
        } catch (FieldValidationException e) {
            System.out.println("\n" + e.getMessage());
            System.out.println("Please try again.");
        }
    }

    public static void viewRealEstateByType(Scanner scanner) {
        System.out.println("Choose what type of Real Estate you are looking for (enter 1 or 2)\n" +
                "\t1. Apartament\n" +
                "\t2. Building");
        RealEstateType realEstateType;
        String typeChoice = scanner.nextLine();
        switch (typeChoice) {
            case "1":
                realEstateType = RealEstateType.APARTMENT;
                break;
            case "2":
                realEstateType = RealEstateType.BUILDING;
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
        List<RealEstate> realEstates = REAL_ESTATE_SERVICE.getAllAvailableByType(realEstateType);
        if (realEstates.isEmpty()) {
            System.out.println("\nNo real estates by this type");
        } else {
            System.out.println(RealEstate.getTableHeader());
            for (RealEstate rlSt : realEstates) {
                System.out.println(rlSt);
            }
        }
    }

    public static void viewAllRealEstates() {
        List<RealEstate> realEstates = REAL_ESTATE_SERVICE.getAllAvailable();
        System.out.println(RealEstate.getTableHeader());
        for (RealEstate item : realEstates) {
            System.out.println(item);
        }
    }
    public static void orderRealEstate(Scanner scanner, Client client) throws EntityNotFoundException, FieldValidationException {
        if (AGREEMENT_SERVICE.getByClientId(client.getId()).isPresent()) {
            System.out.println("You cannot have more than one Real Estate AGREEMENT open. Please pay for your agreement");
            PaymnetAction.askForPayment(scanner, client);
            return;
        }

        List<RealEstate> realEstates = REAL_ESTATE_SERVICE.getAllAvailable();
        System.out.println("Here is a list of all available Real Estates:");
        System.out.println(RealEstate.getTableHeader());
        for (RealEstate realEstate : realEstates) {
            System.out.println(realEstate);
        }

        System.out.print("Enter the id of Real Estate you want to buy: ");
        String choice = scanner.nextLine();

        RealEstate realEstate;
        realEstate = REAL_ESTATE_SERVICE.getAvailableById(parseLong(choice));

        Agreement agreement = new Agreement();
        agreement.setRealEstate(realEstate);
        agreement.setDate(new Date());
        agreement.setDuration("3 months");
        agreement.setAmount(realEstate.getPrice());
        agreement.setClient(client);
        agreement.setStatus("unpaid");

        if (agreement.getClient().equals(REAL_ESTATE_SERVICE.getAvailableById(realEstate.getId()).getSeller())) {
            throw new FieldValidationException("You cannot order your real estate");
        } else System.out.println("The price of Real Estate " + realEstate.getPrice());

        try {
            AGREEMENT_SERVICE.create(agreement, realEstate.getId(), client.getId());
            System.out.println("Your agreement is ready ");

            System.out.println(Agreement.getTableHeader());
            System.out.println(agreement);
        } catch (FieldValidationException e) {
            System.out.println(e.getMessage());
        }

        PaymnetAction.askForPayment(scanner, client);
    }

    private static int parseInt(String rooms) throws FieldValidationException {
        try {
            return Integer.parseInt(rooms);
        } catch (NullPointerException | NumberFormatException e) {
            throw new FieldValidationException(String.format("Specified incorrect rooms amount: %s", rooms), e);
        }
    }

    private static BigDecimal parseDouble(String price) throws FieldValidationException {
        try {
            return BigDecimal.valueOf(Double.parseDouble(price));
        } catch (NullPointerException | NumberFormatException e) {
            throw new FieldValidationException(String.format("Specified incorrect price: %s", price), e);
        }
    }
    private static long parseLong(String choice) throws FieldValidationException {
        try {
            return Long.parseLong(choice);
        } catch (NullPointerException | NumberFormatException e) {
            throw new FieldValidationException(String.format("Specified incorrect id: %s", choice), e);
        }
    }
}
