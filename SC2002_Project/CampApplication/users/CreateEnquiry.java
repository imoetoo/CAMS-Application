package CampApplication.users;

import CampApplication.Camps.Camp;

/**
 * An interface for Attendees to create enquiries about the camps they are interested in
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface CreateEnquiry extends ViewEnquiry {
    /**
     * function to initiate enquiry object and add into enquiry arraylist
     * 
     * @param camp camp object that the enquiry belongs to
     */
    public void createEnquiry(Camp camp);

    /**
     * function to change enquiry content
     * 
     * @param camp camp object that the enquiry belongs to
     */
    public void editEnquiry(Camp camp);

    /**
     * function to delete the enquiry object from the enquiry arraylist
     * 
     * @param camp
     */
    public void deleteEnquiry(Camp camp);
}
