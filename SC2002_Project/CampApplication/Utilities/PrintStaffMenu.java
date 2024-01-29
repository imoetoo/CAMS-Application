package CampApplication.Utilities;

import CampApplication.users.Staff;
/**
 * Staff menu after user has logged in
 *
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class PrintStaffMenu {
    public static void printStaffMenu(String staffName) {
        System.out.printf("======  %s's main page  ======\n", staffName);
        System.out.println("Reminder: You must use the Exit option in the menu to get all changes saved.");
        System.out.println("______________________________________________________");
        System.out.println("Please select an option");
        System.out.println("1) Change password. ");
        System.out.println("2) View all camps");
        System.out.println("3) View your camps");
        System.out.println("4) Create a new camp");
        System.out.println("5) Log out");
    }
}
