package CampApplication.Camps;

import CampApplication.users.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Camp stores information of attendees, committee, suggestions and enquiries
 * 
 * @author group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class Camp extends CampInformation {
    /**
     * attendeeList is the list of students that are attending the camp
     */
    private ArrayList<Student> attendeeList = new ArrayList<Student>();
    /**
     * campCommittee is the list of students that are in the camp committee
     */
    private ArrayList<CampCommittee> campCommittee = new ArrayList<CampCommittee>();
    /**
     * withdrawnAttendee is the list of students that has withdrawn from the camp
     */
    private ArrayList<Student> withdrawnAttendee = new ArrayList<Student>();
    /**
     * enquiryList is the list of enquiries that are submitted by the attendees
     */
    private ArrayList<Enquiry> enquiryList = new ArrayList<Enquiry>();
    /**
     * suggestionList is the list of suggestions that are submitted by the camp
     * committee
     */
    private ArrayList<Suggestion> suggestionList = new ArrayList<>();

    /**
     * Camp constructor
     * 
     * @param campName                name of the camp
     * @param campStartingDate        starting date of the camp
     * @param campEndingDate          ending date of the camp
     * @param registrationClosingDate closing date of the camp registration
     * @param campFaculty             faculty that the camp belongs to
     * @param location                location of the camp
     * @param totalSlots              total number of slots for the camp attendees +
     *                                committee
     * @param campCommitteeSlots      number of slots for the camp committee
     * @param description             description of the camp
     * @param staffInCharge           staff in charge of the camp
     */
    public Camp(String campName, LocalDate campStartingDate, LocalDate campEndingDate,
            LocalDate registrationClosingDate, FacultyInformation campFaculty, String location, int totalSlots,
            int campCommitteeSlots,
            String description, String staffInCharge) {
        super(campName, campStartingDate, campEndingDate, registrationClosingDate, campFaculty, location, totalSlots,
                campCommitteeSlots, description, staffInCharge);
    }

    /**
     * blackList checks if the student is blacklisted from the camp, Arraylist is
     * used to store those who withdrawn from camp
     * 
     * @param student student that is being checked
     * @return true if the student is blacklisted, false if the student is not
     *         blacklisted
     */
    public boolean blackList(Student student) {
        if (withdrawnAttendee.contains(student)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getWithdrawnAttendee returns the list of students that has withdrawn from the
     * camp
     * 
     * @return the list of students that has withdrawn from the camp
     */
    public ArrayList<Student> getWithdrawnAttendee() {
        return this.withdrawnAttendee;
    }

    /**
     * setWithdrawnAttendee sets the list of students that has withdrawn from the
     * camp
     * 
     * @param withdrawnAttendee the list of students that has withdrawn from the
     *                          camp
     */
    public void setWithdrawnAttendee(ArrayList<Student> withdrawnAttendee) {
        this.withdrawnAttendee = withdrawnAttendee;
    }

    /**
     * getAttendeeList returns the list of students that are attending the camp
     * 
     * @return the list of students that are attending the camp
     */
    public ArrayList<Student> getAttendeeList() {
        return this.attendeeList;
    }

    /**
     * setAttendeeList sets the list of students that are attending the camp
     * 
     * @param campAttendee the list of students that are attending the camp
     */
    public void setAttendeeList(ArrayList<Student> campAttendee) {
        this.attendeeList = campAttendee;
    }

    /**
     * getCampCommittee returns the list of students that are in the camp committee
     * 
     * @return the list of students that are in the camp committee
     */
    public ArrayList<CampCommittee> getCampCommittee() {
        return this.campCommittee;
    }

    /**
     * setCampCommittee sets the list of students that are in the camp committee
     * 
     * @param campCommittee the list of students that are in the camp committee
     */
    public void setCampCommittee(ArrayList<CampCommittee> campCommittee) {
        this.campCommittee = campCommittee;
    }

    /**
     * getEnquiryList returns the list of enquiries that are submitted by the
     * attendees
     * 
     * @return the list of enquiries that are submitted by the attendees
     */
    public ArrayList<Enquiry> getEnquiryList() {
        return enquiryList;
    }

    // for student to add/remove enquiry
    /**
     * setEnquiryList sets the list of enquiries that are submitted by the attendees
     * 
     * @param delete             boolean to indicate if the enquiry is to be deleted
     *                           or not
     * @param enquiryToBeEditted enquiry that is to be edited
     */
    public void setEnquiryList(boolean delete, Enquiry enquiryToBeEditted) {
        if (delete) {
            this.enquiryList.remove(enquiryToBeEditted);
            System.out.println("Enquiry succesfully deleted");
        } else {
            this.enquiryList.add(enquiryToBeEditted);
            System.out.println("Enquiry submitted");
        }
    }

    /**
     * addAttendee adds the student into the camp
     * 
     * @param student student that is to be added into the camp
     */
    public void addAttendee(Student student) {
        this.attendeeList.add(student);
    }

    /**
     * addStudentBlacklist adds the student into the withdrawn list
     * 
     * @param student student that is to be added into the withdrawn list
     */
    public void addStudentBlacklist(Student student) {
        this.withdrawnAttendee.add(student);
    }

    /**
     * removeStudent removes the student from the camp and appends them to withdrawn
     * list
     * 
     * @param student student that is to be removed from the camp
     */
    public void removeStudent(Student student) {
        this.attendeeList.remove(student);
        this.withdrawnAttendee.add(student);
        student.getRegisteredCamp().remove(this);
    }

    /**
     * addCommittee adds the student into the camp committee
     * 
     * @param campComp student that is to be added into the camp committee
     */
    public void addCommittee(CampCommittee campComp) {
        this.campCommittee.add(campComp);
    }

    /**
     * setEnquiryList sets the list of enquiries that are submitted by the attendees
     * 
     * @param enquiryList the list of enquiries that are submitted by the attendees
     */
    public void setEnquiryList(ArrayList<Enquiry> enquiryList) {
        this.enquiryList = enquiryList;
    }

    /**
     * getSuggestionList returns the list of suggestions that are submitted by the
     * camp committee
     * 
     * @return the list of suggestions that are submitted by the camp committee
     */
    public ArrayList<Suggestion> getSuggestionList() {
        return this.suggestionList;
    }

    /**
     * adds a new suggestion to the list of suggestions that are submitted by the
     * camp committee
     * 
     * @param content       content of the suggestion
     * @param campCommittee camp committee that submitted the suggestion
     * @return the new suggestion that is added
     */
    public Suggestion addNewSuggestion(String content, CampCommittee campCommittee) {
        Suggestion newSuggestion = new Suggestion(content, this, campCommittee);
        this.suggestionList.add(newSuggestion);
        return newSuggestion;
    }

    /**
     * used for delete and editting suggestion boolean delete to indicate purpose
     * setSuggestionList sets the list of suggestions that are submitted by the camp
     * committee
     * 
     * @param delete                boolean to indicate if the suggestion is to be
     *                              deleted or not
     * @param newContent            new content of the suggestion
     * @param oldContent            old content of the suggestion
     * @param suggestionToBeRemoved suggestion that is to be removed
     */
    public void setSuggestionList(boolean delete, String newContent, String oldContent,
            Suggestion suggestionToBeRemoved) {
        if (delete) {
            suggestionList.remove(suggestionToBeRemoved);
            System.out.println("Successfully deleted suggestion");
        } else {
            for (Suggestion suggestion : suggestionList) {
                if (suggestion.getContent() == oldContent) {
                    suggestion.setContent(newContent);
                    System.out.println("Successfully updated suggestion");
                    return;
                }
            }
        }
    }

    /**
     * printCampDetails prints the interface for users
     */
    public void printCampDetails(boolean isAttendee) {
        System.out.println("Camp Name: " + this.getCampName());
        System.out.println("Camp Starting Date: " + String.valueOf(this.getCampStartingDate()));
        System.out.println("Camp Ending Date: " + String.valueOf(this.getCampEndingDate()));
        System.out.println("Camp Registration Closing Date: " + String.valueOf(this.getRegistrationClosingDate()));
        System.out.println("Camp Faculty: " + String.valueOf(this.getCampFaculty()));
        System.out.println("Camp Location: " + this.getLocation());
        System.out.println("Camp Total Slots: " + String.valueOf(this.getTotalSlots()));
        System.out.println("Camp Committee Slots: " + String.valueOf(this.getCampCommitteeSlots()));
        System.out.println("Camp Description: " + this.getDescription());
        System.out.println("Camp Staff In Charge: " + this.getStaffInCharge());
        if (!isAttendee) {
            System.out.println("Camp Visibility: " + String.valueOf(this.getCampVisibility()));
            System.out.println("Camp Withdrawn Attendee: ");
            for (Student student : this.getWithdrawnAttendee())
                System.out.println(student);

            System.out.println("Camp Attendee: ");
            for (Student student : this.getAttendeeList())
                System.out.println(student.getName());

            System.out.println("Camp Enquiry: ");
            for (Enquiry enquiry : this.getEnquiryList())
                System.out.println(enquiry.getContent());
        }

        System.out.println("Camp Committees: ");
        for (CampCommittee campCom : this.getCampCommittee())
            System.out.println(campCom.getName());

    }

    /**
     * method to find camp location
     * 
     * @return camp location is returned
     */
    public String getCampLocation() {
        return this.getLocation();
    }
};
