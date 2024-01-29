package CampApplication.users;

import CampApplication.Camps.Camp;

/**
 * Represents an Enquiry object to be sent to either staff or committee
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class Enquiry {
    /**
     message to be sent to staff or committee
     */
    private String content = "no content";
    /**
     * boolean to indicate whether the enquiry is replied
     */
    private boolean replied = false;
     
    /**
     * stores student object who sent the enquiry
     */
    Student student;
    /**
     * stores the camp object the enquiry belongs to
     */
     
    Camp camp;
    /**reply message sent by staff or committee  */ 
    private String replies = "no replies";

    // constructor
    /**
     * constructor to initiate enquiry object
     * 
     * @param content message to be sent to staff or committee
     * @param student stores student object who sent the enquiry
     * @param camp    stores the camp object the enquiry belongs to
     * @param replies reply message sent by staff or committee
     */
    public Enquiry(String content, Student student, Camp camp, String replies) {
        this.content = content;
        this.student = student;
        this.camp = camp;
        this.replies = replies;
    }

    // getters and setters
    /**
     * enquiry content getter
     * 
     * @return the message as string
     */
    public String getContent() {
        return content;
    }

    /**
     * enquiry content setter
     * 
     * @param content new message to replace the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * getter to access the reply status of the enquiry
     * 
     * @return true if the enquiry is replied, false otherwise
     */
    public boolean isReplied() {
        return replied;
    }

    /**
     * setter to modify the reply status of the enquiry
     * 
     * @param replied true if the enquiry is replied, false otherwise
     */
    public void setReplied(boolean replied) {
        this.replied = replied;
    }

    /**
     * getter to access the student object sent the enquiry
     * 
     * @return student object
     */
    public Student getStudent() {
        return student;
    }

    /**
     * setter to modify student object in enquiry
     * 
     * @param student student object to be included in enquiry
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * getter to access camp object the enquiry belongs
     * 
     * @return camp object the enquiry belongs to
     */
    public Camp getCamp() {
        return camp;
    }

    /**
     * setter to modify camp object the enquiry belongs
     * 
     * @param camp camp object the enquiry belongs to
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    /**
     * getter to access the reply status of an enquiry
     * 
     * @return true if the enquiry is replied
     */
    public String getReplies() {
        return replies;
    }

    /**
     * setter to modify the reply status of the enquiry
     * 
     * @param replies new reply message to be included in the enquiry
     */
    public void setReplies(String replies) {
        this.replies = replies;
    }

}
