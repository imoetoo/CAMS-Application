package CampApplication.users;

import CampApplication.Camps.Camp;

/**
 * An interface with a function to list out all suggestions in a camp and prompt user to choose any to reply
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface AnswerSuggestion extends ViewSuggestion {

    /**
     * function to list out all suggestions in a camp and prompt user to choose any to reply
     * @param camp the respective camp
     */


    public void approveSuggestion(Camp camp);

}