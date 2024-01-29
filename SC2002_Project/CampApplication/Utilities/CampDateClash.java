package CampApplication.Utilities;

import java.util.ArrayList;

import CampApplication.Camps.Camp;

/**
 * Function to check if the camp date that the student registered clashes with another available camp
 *
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class CampDateClash {
    public static boolean campDateClash(Camp camp1, Camp camp2) {
        return !(camp1.getCampEndingDate().isBefore(camp2.getCampStartingDate())
                || camp1.getCampStartingDate().isAfter(camp2.getCampEndingDate()));
    }

    public static boolean studentCampDateClash(Camp camp, ArrayList<Camp> campList) {
        for (Camp campInList : campList) {
            if (CampDateClash.campDateClash(camp, campInList)) {
                return true;
            }
        }
        return false;
    }
}
