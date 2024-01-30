package com.solvd;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        System.out.println("Welcome to the Real Estate Agency console app. Please choose an action");

        while (!exitLoop) {
            System.out.println("\nEnter '0' to register as a new client");
            System.out.println("Enter '1' to login and confirm your identity.");
            System.out.println("Enter '2' to Exit.");
            System.out.print("Your choice: ");

            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    UserAction.registerClient(scanner);
                    break;
                case "1":
                    UserAction.login(scanner);
                    break;
                case "2":
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter '0', '1' or '2'.");
            }
        }
    }
}
