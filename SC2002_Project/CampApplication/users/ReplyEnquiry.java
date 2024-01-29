package CampApplication.users;

import CampApplication.Camps.Camp;

/**
 * An interface to inject message into message content of enquiry object
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface ReplyEnquiry extends ViewEnquiry {
    /**
     * function to inject message into message content of enquiry object
     * 
     * @param camp the camp object which enquiry belongs to
     */
    public void replyEnquiry(Camp camp);
}