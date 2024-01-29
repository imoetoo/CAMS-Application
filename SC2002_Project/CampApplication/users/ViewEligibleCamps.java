package CampApplication.users;

import java.util.ArrayList;
import CampApplication.Camps.Camp;
import java.util.Scanner;

/**
 * An interface for Student to view eligible camps to register as Attendee or Committee
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface ViewEligibleCamps {
    /**
     * view eligible camps for students to register
     * @param campList pass in the eligible camp list
     * @param sc pass in scanner
     */
    public void viewCamp(ArrayList<Camp> campList, Scanner sc);
}
