package CampApplication.users;

import CampApplication.Camps.Camp;
import CampApplication.Utilities.GetInt;
import CampApplication.Utilities.GetString;

/**
 * Represents the Camp menu that Staff can do by listing out the actions available
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class CampMenuStaff {
    /**
     * function to enters staff user interface
     * 
     * @param camp  camp object which is refering to
     * @param staff staff object who is making decisions
     */
    public static void campMenuStaff(Camp camp, Staff staff) {

        final int logOutIndex = 13;
        int choice;

        do {
            printCampStaffMenu(camp);
            choice = GetInt.getInt("\nEnter your choice:");
            switch (choice) {
                case (1):
                    staff.printCampDetails(camp);
                    break;
                case (2):
                    staff.editCamp(camp);
                    break;
                case (3):
                    staff.deleteCamp(camp);
                    break;
                case (4):
                    System.out.println("the list of attendee:");
                    for (Student stu : camp.getAttendeeList()) {
                        System.out.println(stu.getName());
                    }
                    break;
                case (5):
                    System.out.println("the list of committee:");
                    for (Student stu : camp.getCampCommittee()) {
                        System.out.println(stu.getName());
                    }
                    break;
                case (6):
                    camp.setCampVisibility();
                    String visible = (camp.getCampVisibility() == true) ? "visible" : "not visible";
                    System.out.printf("Camp %s has set to %s\n", camp.getCampName(), visible);
                    break;
                case (7):
                    staff.viewSuggestion(camp);
                    break;
                case (8):
                    staff.approveSuggestion(camp);
                    break;
                case (9):
                    staff.viewEnquiry(camp);
                    break;
                case (10):
                    staff.replyEnquiry(camp);
                    break;
                case (11):
                    String filterCampName = null;
                    String filterAttendeeName = null;
                    String filterLocation = null;
                    int choice2;
                    int choice3;
                    do {
                        System.out.println("do you want to filter your report? 1.no 2.yes 3.exit");
                        choice2 = GetInt.getInt("Enter your choice \n");
                        switch (choice2) {
                            case 1:
                                staff.generateReport(null, null, null);
                                break;
                            case 2:
                                do {
                                    System.out.println("How do you want to filter your report?");
                                    System.out.println("1. CampName 2. AttendeeName 3. CampLocation 4.exit");
                                    choice3 = GetInt.getInt("Enter your choice \n");
                                    switch (choice3) {
                                        case 1:
                                            filterCampName = GetString.getString("Enter the camp name.\n");
                                            staff.generateReport(filterCampName, null, null);
                                            break;
                                        case 2:
                                            filterAttendeeName = GetString.getString("Enter the attendee name\n");
                                            staff.generateReport(null, filterAttendeeName, null);
                                            break;
                                        case 3:
                                            filterLocation = GetString.getString("Enter the location\n");
                                            staff.generateReport(null, null, filterLocation);
                                            break;
                                        case 4:
                                            System.out.println("Exiting..");
                                            break;
                                        default:
                                            System.out.println("Invalid input.Try again");
                                            break;
                                    }
                                } while (choice3 != 4);
                                break;
                            case 3:
                                System.out.println("Exiting..");
                                break;
                            default:
                                System.out.println("Invalid input.Try again");
                                break;
                        }
                    } while (choice2 != 3);
                case 12:
                    staff.generateEnquiryReport();
                    break;
                case (logOutIndex):
                    System.out.println("Returning to staff page...\n\n");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (choice != logOutIndex);
    }

    /**
     * prints out the menu with list of available actions
     * 
     * @param camp camp object the staff wish to take action
     */
    public static void printCampStaffMenu(Camp camp) {
        System.out.printf("""
                =========   Camp %s Staff Page   ========

                What do you want to do?
                (1) View camp details
                (2) Edit camp details
                (3) Delete camp
                (4) View Camp attendees
                (5) View Camp Committees
                (6) Change camp visibility
                (7) View suggestions
                (8) Answer Suggestions
                (9) View enquiries
                (10) Answer enquiries
                (11) Generate report
                (12) Generate enquiry report
                (13) Exit
                """, camp.getCampName());
    }
}
