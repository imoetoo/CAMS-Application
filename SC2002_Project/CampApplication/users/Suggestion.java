package CampApplication.users;

import CampApplication.Camps.Camp;

/**
 * Represents suggestion class that contains its status, content,the camp it belongs to
 * and the camp committee member
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class Suggestion {
    // suggestion message to be sent to staff or committee
    /**
     * suggestion message to be sent to staff or committee
     */
    private String content;
    // boolean to indicate whether the suggestion is replied
    /**
     * boolean to indicate whether the suggestion is replied
     */
    private boolean answered = false;
    // enum to indicate the progress of approval
    /**
     * enum to indicate the progress of approval
     */
    private Status status = Status.INPROGRESS;
    /**
     * stores committee object who sent the enquiry
     */

    // stores committee object who sent the enquiry
    private CampCommittee campCommittee;
    /**
     * store camp object the suggestion belongs to
     */
    // store camp object the suggestion belongs to
    private Camp camp;

    /**
     * constructor to initiate suggestion object
     * 
     * @param content       suggestion content
     * @param camp          camp object the suggestion object belongs to
     * @param campCommittee committee object the suggestion belongs to
     */
    public Suggestion(String content, Camp camp, CampCommittee campCommittee) {
        this.content = content;
        this.camp = camp;
        this.campCommittee = campCommittee;
    }

    // getters and setters
    /**
     * getter to access the progess of suggestion
     * 
     * @return progress status in enum Status
     */
    public Status getStatus() {
        return status;
    }

    // getters and setters
    /**
     * setter to modify the progess of suggestion
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * getter to access the suggestion content in suggestion object
     * 
     * @return suggestion content as string
     */
    public String getContent() {
        return content;
    }

    /**
     * setter to modify suggestion content in suggestion object
     * 
     * @param content new suggestion content to replace old suggestion
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * getter to access the reply status of suggestion object
     * 
     * @return true if the suggestion is replied, false otherwise
     */
    public boolean isAnswered() {
        return answered;
    }

    /**
     * setter to modify the reply status of suggestion object to replied
     */
    public void setAnswered() {
        this.answered = true;
    }

    /**
     * getter to access the committee who sent the suggestion
     * 
     * @return campCommittee object
     */
    public CampCommittee getCampCommittee() {
        return campCommittee;
    }

    /**
     * setter to modify the committee who set the suggestion
     * 
     * @param campCommittee committee who set the suggestion
     */
    public void setCampCommitteeMember(CampCommittee campCommittee) {
        this.campCommittee = campCommittee;
    }

    /**
     * getter to access camp object the suggestion belongs to
     * 
     * @return camp object
     */
    public Camp getCamp() {
        return camp;
    }

    /**
     * setter to modify the camp object the suggestion belongs to
     * 
     * @param camp camp object
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    // methods
    /**
     * function to set status of the suggestion
     * 
     * @param approve set status to APPROVED if true, REJECTED if false
     */
    public void approveSuggestion(boolean approve) {
        this.answered = true;
        if (approve) {
            this.status = Status.REJECTED;
            return;
        }
        this.status = Status.APPROVED;
        this.campCommittee.addPoint();
    }
}
