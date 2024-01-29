package CampApplication.users;

import CampApplication.Camps.Camp;
import CampApplication.Utilities.GetInt;

/**
 * Represents Camp menu for Camp Committees which list out the actions that they can do
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class CampMenuCommittee {

    public static void campMenuCommittee(Camp camp, CampCommittee committee) {

        final int logOutIndex = 11;
        int choice;

        do {
            printCampCommitteeMenu(camp);
            choice = GetInt.getInt("\nEnter your choice:");
            switch (choice) {
                case (1):
                    camp.printCampDetails(false);
                    break;
                case (2):
                    committee.printPoints();
                    break;
                case (3):
                    committee.viewEnquiry(camp);
                    break;
                case (4):
                    committee.replyEnquiry(camp);
                    break;
                case (5):
                    committee.submitSuggestion(camp);
                    break;
                case (6):
                    committee.viewSuggestion(camp);
                    break;
                case (7):
                    committee.editSuggestion(camp);
                    break;
                case (8):
                    committee.deleteSuggestion(camp);
                    break;
                case (9):
                    int choice1;
                    int choice2;
                    System.out.println("Do you want to filter the report");
                    choice1 = GetInt.getInt("enter your choice: 1. no filter  else,filter \n");
                    switch (choice1) {
                        case 1:
                            committee.generateReport(false, false);
                            break;
                        default:
                            do {
                                choice2 = GetInt.getInt("1.filter by Attendees 2.filter by committees 3.Exit \n ");
                                switch (choice2) {
                                    case 1:
                                        committee.generateReport(true, false);
                                        break;
                                    case 2:
                                        committee.generateReport(false, true);
                                        break;
                                    case 3:
                                        System.out.println("Exiting...");
                                        break;
                                    default:
                                        System.out.println("Invalid input. Please try again.");
                                        break;
                                }
                            } while (choice2 != 3);
                            break;
                    }
                    break;
                case (10):
                    committee.generateEnquiryReport();
                case (logOutIndex):
                    System.out.println("Returning to student page...\n\n");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (choice != logOutIndex);
    }

    /*
     * print camp committee menu, which is the interface for camp committee members
     */
    public static void printCampCommitteeMenu(Camp camp) {
        System.out.printf("""
                =========   Camp %s Committee's Page   ========

                    What do you want to do?
                    (1) View camp details
                    (2) View your points
                    (3) View enquiry
                    (4) Reply an enquiry
                    (5) Submit a suggestion
                    (6) View submited suggestion
                    (7) Edit suggestion
                    (8) Delete a suggestion
                    (9) Generate report
                    (10) Generate enquiry report
                    (11) Exit
                    """, camp.getCampName());
    }
}
