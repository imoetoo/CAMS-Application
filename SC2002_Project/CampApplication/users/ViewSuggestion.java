package CampApplication.users;

import CampApplication.Camps.Camp;

/**
 * An interface to list out the suggestions of a camp object
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface ViewSuggestion {
    /**
     * list out the suggestions of a camp object
     * 
     * @param camp the target camp object to be view
     */
    public void viewSuggestion(Camp camp);
}