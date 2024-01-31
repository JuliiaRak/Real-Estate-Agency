package com.solvd;

import com.solvd.domain.Agreement;
import com.solvd.domain.Client;
import com.solvd.domain.Meeting;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.service.AgreementService;
import com.solvd.service.MeetingService;
import com.solvd.service.impl.AgreementServiceImpl;
import com.solvd.service.impl.MeetingServiceImpl;

import java.util.Optional;
import java.util.Scanner;

public class PaymentAction {
    private static final AgreementService AGREEMENT_SERVICE = new AgreementServiceImpl();
    private static final MeetingService MEETING_SERVICE = new MeetingServiceImpl();

    public static void askForPayment(Scanner scanner, Client client) {
        System.out.println("Please pay for your agreement");
        System.out.println("Enter 1 to pay, or 0 to exit");
        System.out.print("Your choice: ");

        String choiceToPay = scanner.nextLine();
        switch (choiceToPay) {
            case "1":
                try {
                    payForAgreement(client);
                } catch (EntityNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "0":
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public static void payForAgreement(Client client) throws EntityNotFoundException {
        Optional<Agreement> agreement = AGREEMENT_SERVICE.getByClientId(client.getId());
        if (agreement.isEmpty()) {
            System.out.println("Sorry, you have no agreement to pay for");
        } else {
            System.out.println("Thank you for paying for agreement");
            RealEstate realEstate = agreement.get().getRealEstate();
            AGREEMENT_SERVICE.deleteById(agreement.get().getId());
            for (Meeting meetingToDelete : MEETING_SERVICE.getByRealEstate(realEstate)) {
                MEETING_SERVICE.deleteById(meetingToDelete.getId());
            }
        }
    }
}
