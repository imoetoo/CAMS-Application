package CampApplication.users;

import CampApplication.Camps.Camp;

/**
 * An interface to list out the enquiries of target camp object to be viewed
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface ViewEnquiry {
    /**
     * list out the enquiries of a camp object
     * 
     * @param camp the target camp object to be viewed
     */
    public void viewEnquiry(Camp camp);
}
