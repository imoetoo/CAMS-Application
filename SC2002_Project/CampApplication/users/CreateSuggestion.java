package CampApplication.users;

import CampApplication.Camps.Camp;

/**
 * An interface for Camp committee to create Suggestions for the camp
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface CreateSuggestion extends ViewSuggestion {
    /**
     * function to initiate suggestion object and add into suggestion arraylist in
     * the camp and committee object
     * 
     * @param camp camp object that the suggestion belongs to
     */
    public void submitSuggestion(Camp camp);

    /**
     * function to change suggestion content
     * 
     * @param camp camp object that the suggestion belongs to
     */
    public void editSuggestion(Camp camp);

    /**
     * function to delete the suggestion object from the suggestion arraylist
     * 
     * @param camp camp object that the suggestion belongs to
     */
    public void deleteSuggestion(Camp camp);
}
