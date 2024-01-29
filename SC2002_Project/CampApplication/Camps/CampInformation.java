package CampApplication.Camps;

import CampApplication.users.FacultyInformation;
import java.time.LocalDate;
/**
 * CampInformation stores the information of all the camp details
 * @author group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class CampInformation {
    /**
     * campName is the name of the camp
     */
    private String campName = "no name is assigned";
    /**
     * campFaculty is the faculty that the camp belongs to
     */
    private FacultyInformation campFaculty;
    /**
     * campStartingDate is the starting date of the camp
     */
    private LocalDate campStartingDate;
    /**
     * campEndingDate is the ending date of the camp
     */
    private LocalDate campEndingDate;
    /**
     * registrationClosingDate is the closing date of the camp registration
     */
    private LocalDate registrationClosingDate;
    /**
     * location is the location of the camp
     */
    private String location;
    /**
     * totalSlots is the total number of slots for the camp attendees + committee
     */
    private int totalSlots;
    /**
     * campCommitteeSlots is the number of slots for the camp committee
     */
    private int campCommitteeSlots;
    /**
     * description is the description of the camp
     */
    private String description = "no description is added by the host";
    /**
     * staffInCharge is the staff in charge of the camp
     */
    private String staffInCharge;
    /**
     * campVisibility is the visibility of the camp on means all students can see, off means only the staff can see
     */
    private boolean campVisibility = false;


    /**
     * Camp information constructor
     * @param campName name of the camp
     * @param campStartingDate starting date of the camp
     * @param campEndingDate ending date of the camp
     * @param registrationClosingDate closing date of the camp registration
     * @param campFaculty faculty that the camp belongs to
     * @param location location of the camp
     * @param totalSlots   total number of slots for the camp attendees + committee
     * @param campCommitteeSlots number of slots for the camp committee
     * @param description description of the camp
     * @param staffInCharge staff in charge of the camp
     */
    public CampInformation(String campName, LocalDate campStartingDate, LocalDate campEndingDate,
            LocalDate registrationClosingDate, FacultyInformation campFaculty, String location, int totalSlots,
            int campCommitteeSlots,
            String description, String staffInCharge) {
        this.campName = campName;
        this.campStartingDate = campStartingDate;
        this.campEndingDate = campEndingDate;
        this.registrationClosingDate = registrationClosingDate;
        this.campFaculty = campFaculty;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
    }

    /**
     * getter method for camp name
     * @return camp name
     */
    public String getCampName() {
        return campName;
    }

    /**
     * setter method for camp name
     * @param campName name of the camp
     */
    public void setCampName(String campName) {
        this.campName = campName;
    }

    /**
     * getter method for camp faculty
     * @return camp faculty
     */
    public FacultyInformation getCampFaculty() {
        return campFaculty;
    }

    /**
     * setter method for camp faculty
     * @param campFaculty faculty that the camp belongs to
     */
    public void setCampFaculty(FacultyInformation campFaculty) {
        this.campFaculty = campFaculty;
    }

    /**
     * getter method for camp starting date
     * @return camp starting date
     */
    public LocalDate getCampStartingDate() {
        return campStartingDate;
    }

    /**
     * setter method for camp starting date
     * @param campStartingDate starting date of the camp
     */
    public void setCampStartingDate(LocalDate campStartingDate) {
        this.campStartingDate = campStartingDate;
    }

    /**
     * getter method for camp ending date
     * @return camp ending date
     */
    public LocalDate getCampEndingDate() {
        return campEndingDate;
    }

    /**
     * setter method for camp ending date
     * @param campEndingDate ending date of the camp
     */
    public void setCampEndingDate(LocalDate campEndingDate) {
        this.campEndingDate = campEndingDate;
    }

    /**
     * getter method for registration closing date
     * @return registration closing date
     */
    public LocalDate getRegistrationClosingDate() {
        return registrationClosingDate;
    }

    /**
     * setter method for registration closing date
     * @param registrationClosingDate closing date of the camp registration
     */
    public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
        this.registrationClosingDate = registrationClosingDate;
    }

    /**
     * getter method for location
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter method for location
     * @param location location of the camp
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * getter method for total slots camp attendees + committee
     * @return total slots
     */
    public int getTotalSlots() {
        return totalSlots;
    }

    /**
     * setter method for total slots
     * @param totalSlots total number of slots for the camp attendees + committee
     */
    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    /**
     * getter method for camp committee slots
     * @return camp committee slots
     */
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    /**
     * setter method for camp committee slots
     * @param campCommitteeSlots number of slots for the camp committee
     */
    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    /**
     * getter method for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter method for description
     * @param description description of the camp
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter method for staff in charge
     * @return staff in charge
     */
    public String getStaffInCharge() {
        return staffInCharge;
    }

    /**
     * setter method for staff in charge
     * @param staffInCharge staff in charge of the camp
     */
    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    /**
     * getter method for camp visibility
     * @return camp visibility
     */
    public boolean getCampVisibility() {
        return campVisibility;
    }

    /**
     * setter method for camp visibility If camp visibility is true, then swap to false If camp visibility is false, then swap to true
     */
    public void setCampVisibility() {
        this.campVisibility = !this.campVisibility;

    }

}