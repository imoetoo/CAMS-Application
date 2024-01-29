package CampApplication.users;

import CampApplication.Camps.Camp;
import CampApplication.Utilities.GetInt;

/**
 * Represents camp menu for Student
 * List out the actions the student can do such as print camp details, submit
 * enquiries etc
 * 
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class CampMenuStudent {
    /**
     * Prints the menu of actions that the student can do for the camp they
     * registered
     * 
     * @param camp    The camp that the student registered
     * @param student The student
     */
     
    public static void campMenuStudent(Camp camp, Student student) {

        final int logOutIndex = 7;
        int choice;

        do {
            printCampStudentMenu(camp);
            choice = GetInt.getInt("\nEnter your choice:");
            switch (choice) {
                case (1):
                    camp.printCampDetails(true);
                    break;
                case (2):
                    student.viewEnquiry(camp);
                    break;
                case (3):
                    student.createEnquiry(camp);
                    break;
                case (4):
                    student.editEnquiry(camp);
                    break;
                case (5):
                    student.deleteEnquiry(camp);
                    break;
                case (6):
                    student.withdrawFromCamp(camp);
                    break;
                case (logOutIndex):
                    System.out.println("Returning to student page...\n\n");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (choice != logOutIndex && !camp.getWithdrawnAttendee().contains(student));
    }

    /**
     * prints out the student
     * 
     * @param camp
     */
    public static void printCampStudentMenu(Camp camp) {
        System.out.printf("""
                =========   Camp %s Attendee's Page   ========

                What do you want to do?
                (1) View camp details
                (2) View enquiry
                (3) Send an enquiry
                (4) Edit an enquiry
                (5) Delete an enquiry
                (6) Withdraw from camp
                (7) Exit
                """, camp.getCampName());
    }
}
