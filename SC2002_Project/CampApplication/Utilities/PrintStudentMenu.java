package CampApplication.Utilities;

import CampApplication.users.Student;

/**
 * Student menu after user has logged in
 *
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class PrintStudentMenu {
    public static void printStudentMenu(String studentName) {
        System.out.printf("======  %s's main page  ======\n", studentName);
        System.out.println("Reminder: You must use the Exit option in the menu to get all changes saved.");
        System.out.println("______________________________________________________");
        System.out.println("Please select an option");
        System.out.println("1) Change password. ");
        System.out.println("2) View available camps according to your faculty");
        System.out.println("3) Select the camps to register as camp attendee or committee");
        System.out.println("4) View your registered camps");
        System.out.println("5) Log out");
    }
}
