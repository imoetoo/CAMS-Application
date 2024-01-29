package CampApplication.users;

import java.util.ArrayList;
import java.util.Scanner;

import CampApplication.Camps.Camp;

/**
 * An interface for Staff to view camps
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface ViewCampStaff {
    /**
     * view  camp
     * @param campList pass in the specific camp
     * @param sc pass in scanner
     */
    public void viewCamp(ArrayList<Camp> campList, Scanner sc);

}
