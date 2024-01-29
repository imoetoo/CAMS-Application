package CampApplication.users;

import java.time.LocalDate;
import java.util.*;
import CampApplication.Camps.Camp;
import CampApplication.Utilities.*;

/**
 * Represents a student user in the CAMS application. Can be either a committee
 * member or a camp attendee.
 * @author group2
 * @version 23/11/2023
 * @since 01/11/2023
 */

public class Student extends User implements ViewEligibleCamps, CreateEnquiry {

    /**
     * isACampCommittee is a boolean to check if the student is a camp committee
     */
    private boolean isACampCommittee = false;
    /**
     * userDetails is a enum to classify user as student
     */
    private UserDetails userDetails = UserDetails.STUDENT;
    /**
     * registeredCamp is an arraylist to store the camp that the student registered
     */
    private ArrayList<Camp> registeredCamp = new ArrayList<>();
    /**
     * campCommittee is a camp committee object to store the camp committee object
     */
    private CampCommittee campCommittee = null;
    /**
     * registeredCampAsCommittee is a camp object to store the camp that the student
     * registered as committee
     */
    private Camp registeredCampAsCommittee = null;

    /**
     * Store the list of students registered for camp
     */
    private static ArrayList<Student> listofStudents = new ArrayList<>();

    /**
     * Constructor for Student class
     * 
     * @param name     name of the student
     * @param email    email of the student
     * @param faculty  faculty of the student
     * @param UserID   UserID of the student
     * @param password password of the student
     */
    public Student(String name, String email, String faculty, String UserID, String password) {
        super(name, email, faculty, UserID, password);
    }

    /**
     * getter for campCommittee
     * 
     * @return campCommittee
     */
    public CampCommittee getCampCommittee() {
        return campCommittee;
    }

    /**
     * setter for campCommittee
     * 
     * @param campCommittee campCommittee
     */
    public void setCampCommittee(CampCommittee campCommittee) {
        this.campCommittee = campCommittee;
    }

    /**
     * getter for ListofStudents
     * 
     * @return
     */
    public static ArrayList<Student> getListofStudents() {
        return listofStudents;
    }

    /**
     * setter for listofstudents
     * 
     * @param listofStudents
     */
    public void setListofStudents(ArrayList<Student> listofStudents) {
        Student.listofStudents = listofStudents;
    }

    /**
     * getter for registeredCampAsCommittee
     * 
     * @return registeredCampAsCommittee
     */
    public Camp getRegisteredCampAsCommittee() {
        return registeredCampAsCommittee;
    }

    /**
     * setter for registeredCampAsCommittee
     * 
     * @param registeredCampAsCommittee registeredCampAsCommittee
     */
    public void setRegisteredCampAsCommittee(Camp registeredCampAsCommittee) {
        this.registeredCampAsCommittee = registeredCampAsCommittee;
    }

    /**
     * getter for registeredCamp
     * 
     * @return registeredCamp
     */
    public ArrayList<Camp> getRegisteredCamp() {
        return registeredCamp;
    }

    /**
     * Add or remove camp from registeredCamp
     * 
     * @param camp  camp to be added or removed
     * @param index index of the camp to be removed
     * @param add   true if camp is to be added, false if camp is to be removed
     */
    public void setRegisteredCamp(Camp camp, int index, boolean add) {
        if (add) {
            this.registeredCamp.add(camp);
        } else {
            this.registeredCamp.remove(index);
        }
    }

    /**
     * getter for userDetails
     * 
     * @return isACampCommittee
     */

    public boolean isACampCommittee() {
        return isACampCommittee;
    }

    /**
     * setter for isACampCommittee
     * 
     * @param isACampCommittee isACampCommittee
     */
    public void setisACampCommittee(boolean isACampCommittee) {
        this.isACampCommittee = isACampCommittee;
    }

    /**
     * For student to register as a camp committee
     * 
     * @param camp camp to be registered as committee
     * @return true if successfully registered, false if not
     */
    public boolean registerCampCommittee(Camp camp) {
        if (isEligibleAsCampCommittee(camp)) {
            CampCommittee campcomp = new CampCommittee(name, email, String.valueOf(facultyInformation), UserID,
                    password, camp, this);
            camp.addCommittee(campcomp);
            setisACampCommittee(true);
            setRegisteredCampAsCommittee(camp);
            // setRegisteredCamp(camp, 0, true);
            this.campCommittee = campcomp;
            System.out.println("Succesfully register as committee for camp " + camp.getCampName());
            return true;
        } else {
            return false;
        }

    }

    /**
     * For students to register camp. Checks for eligibility and prompts for input
     * 
     * @param campList list of camps
     * @return index of the camp in campList that the student registered, -1 if no
     *         camp is registered
     */
    public int registerCamp(ArrayList<Camp> campList) {
        /**
         * eligibleCamps is an arraylist to store the eligible camps for the student to
         * join
         */
        ArrayList<Camp> eligibleCamps = new ArrayList<>();
        /**
         * displayNum is an integer to store the display of the index of a camp
         */
        int displayNum = 1;

        for (Camp camp : campList) {
            // condition checking if eligible or not
            if (isEligibleCamp(camp, false)) {
                eligibleCamps.add(camp);
            }
        }

        if (eligibleCamps.size() == 0) {
            System.out.println("No available camps");
            return -1;
        }

        System.out.println("The list of available camps:");
        for (Camp camp : eligibleCamps) {
            System.out.printf("""
                    (%s) %s
                        Attendee: (%s/%s)
                        Committee: (%s/%s)\n
                    """, String.valueOf(displayNum++), camp.getCampName(),
                    String.valueOf(camp.getAttendeeList().size()),
                    String.valueOf(camp.getTotalSlots() - camp.getCampCommitteeSlots()),
                    String.valueOf(camp.getCampCommittee().size()), String.valueOf(camp.getCampCommitteeSlots()));
        }
        System.out.printf("(%s) Exit\n", String.valueOf(displayNum));
        // prompting input
        int selection = GetInt.getInt("Select camp to register:");
        while (selection <= 0 || selection > displayNum) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("Select camp to register:");
        }
        if (selection == displayNum) { // exit
            return -1;
        }
        Camp registeringCamp = eligibleCamps.get(selection - 1);

        if (registeringCamp.getWithdrawnAttendee().contains(this)) {
            System.out.println("You previously withdrawn from this camp, you cannot register for it again");
            return -1;
        }
        int campRole = 1;

        while (campRole != 0) {
            campRole = GetInt.getInt("""
                    Which role you want to register for?
                    (1) Attendee
                    (2) Committee\n
                    """);
            switch (campRole) {
                case 1:
                    if (registeringCamp.getAttendeeList().size() == registeringCamp.getTotalSlots()
                            - registeringCamp.getCampCommitteeSlots()) {
                        System.out.println("The attendee slots are full!");
                        return -1;
                    }
                    this.setRegisteredCamp(registeringCamp, 0, true);
                    System.out.printf("Successfully registered for camp %s\n", registeringCamp.getCampName());
                    registeringCamp.addAttendee(this);
                    campRole = 0;
                    break;
                case 2:
                    if (registerCampCommittee(registeringCamp)) {
                        campRole = 0;
                    }
                    break;
                default:
                    System.out.println("Invalid entry. Please try again");
            }
        }

        return selection - 1;
    }

    @Override
    /**
     * for students to view camp
     * 
     * @param campList list of camps
     * @param sc       scanner
     */
    public void viewCamp(ArrayList<Camp> campList, Scanner sc) {
        /**
         * displayNum is an integer to store the display of the index of a camp
         */
        int displayNum = 1;
        /**
         * index is an integer to store the index of the camp in campList
         */
        int index = 0;
        /**
         * eligibleCamps is an arraylist to store the eligible camps for the student to
         * join
         */
        ArrayList<Camp> eligibleCamps = new ArrayList<>();
        /**
         * eligibleCampsIndex is an arraylist to store the index of the eligible camps
         * for the student to join
         */
        for (Camp camp : campList) {
            // condition checking
            if (isEligibleCamp(camp, true)) {
                eligibleCamps.add(camp);
            }
            index++;
        }
        int size = eligibleCamps.size();
        if (size == 0) {
            System.out.println("No available camps");
            return;
        }
        Camp[] tempCampList = eligibleCamps.toArray(new Camp[size]);
        String sortByDate = GetString.getString("Do you want to sort by date? (enter y if yes)");
        if (sortByDate.equals("y")) {
            CampMergeSort.mergeSortDate(tempCampList);
        } else {
            CampMergeSort.mergeSortAlphabet(tempCampList);
        }

        System.out.println("The list of available camps:");
        for (Camp camp : tempCampList) {
            System.out.printf("""
                    (%s) %s
                    Starting date: %s
                    Attendee: (%s/%s)
                    Committee: (%s/%s)\n
                    """, String.valueOf(displayNum++), camp.getCampName(), String.valueOf(camp.getCampStartingDate()),
                    String.valueOf(camp.getAttendeeList().size()),
                    String.valueOf(camp.getTotalSlots() - camp.getCampCommitteeSlots()),
                    String.valueOf(camp.getCampCommittee().size()), String.valueOf(camp.getCampCommitteeSlots()));
        }
        System.out.println("Press enter to exit");
        sc.nextLine();
        return;
    }

    /**
     * The interface where students view camp
     * 
     * @param campList list of camps
     */
    public void campInterface(ArrayList<Camp> campList) {
        /**
         * displayNum is an integer to store the display of the index of a camp
         */
        int displayNum = 1;
        if (registeredCamp.size() == 0 && registeredCampAsCommittee == null) {
            System.out.println("No currently joined camp");
            return;
        }
        System.out.println("Currently joined camps:");
        if (registeredCampAsCommittee != null) {
            System.out.printf("""
                    (%s) %s
                    *Registered as committee\n
                    """, String.valueOf(displayNum++), registeredCampAsCommittee.getCampName());
        }
        for (Camp camp : this.registeredCamp) {
            System.out.printf("""
                    (%s) %s\n
                    """, String.valueOf(displayNum++), camp.getCampName());
        }
        System.out.printf("(%s) Exit\n", String.valueOf(displayNum));

        // prompting input
        int selection = GetInt.getInt("Select camp to view:");
        while (selection <= 0 || selection > displayNum) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("Select camp to view:");
        }
        if (selection == displayNum) {
            return;
        }
        if (selection == 1 && registeredCampAsCommittee != null) {
            CampMenuCommittee.campMenuCommittee(this.registeredCampAsCommittee, this.campCommittee);
        } else if (registeredCampAsCommittee != null) {
            Camp camp = this.registeredCamp.get(selection - 2);
            CampMenuStudent.campMenuStudent(camp, this);
        } else {
            Camp camp = this.registeredCamp.get(selection - 1);
            CampMenuStudent.campMenuStudent(camp, this);
        }
        return;
    }

    // Enquiry
    @Override
    /**
     * for students to create enquiry
     * 
     * @param camp camp to create enquiry
     */
    public void createEnquiry(Camp camp) {
        String message = GetString.getString("Enter your message in the enquiry:");
        String replies = "No replies available.";
        Enquiry newEnquiry = new Enquiry(message, this, camp, replies);
        camp.setEnquiryList(false, newEnquiry);
    }

    @Override
    /**
     * for students to view enquiry they have submitted
     * 
     * @param camp camp to view enquiry
     */
    public void viewEnquiry(Camp camp) {
        /**
         * enquiryList is an arraylist to store the enquiries created
         */
        ArrayList<Enquiry> enquiryList = new ArrayList<>();

        /**
         * an integer to store the index of options
         */
        int displayNum = 1;

        for (Enquiry enquiry : camp.getEnquiryList()) {
            if (enquiry.getStudent() == this) {
                enquiryList.add(enquiry);

            }
        }
        if (enquiryList.size() == 0) {
            System.out.println("No submitted eunquiry");
            return;
        }
        // print out the content for each enquiry associated with the student
        System.out.println("The list of enquiries created:");
        for (Enquiry enquiry : enquiryList) {
            {
                System.out.printf("""
                        (%s) %s
                        reply: %s\n
                        """, String.valueOf(displayNum++), enquiry.getContent(), enquiry.getReplies());
            }
        }

    }

    @Override
    /**
     * for students to edit enquiry
     * 
     * @param camp camp to edit enquiry
     */
    public void editEnquiry(Camp camp) {

        /**
         * displayNum is an integer to store the display of the index of options
         */
        int displayNum = 1;
        /**
         * enquiryList is an arraylist to store the enquiries created
         */
        ArrayList<Enquiry> enquiryList = new ArrayList<>();

        // print out the content for each enquiry associated with the student
        for (Enquiry enquiry : camp.getEnquiryList()) {
            if (enquiry.getStudent() == this && !enquiry.isReplied()) {
                enquiryList.add(enquiry);

            }
        }

        if (enquiryList.size() == 0) {
            System.out.println("No editable enquiries");
            return;
        }

        System.out.println("The list of enquiries created:");
        for (Enquiry enquiry : enquiryList) {
            System.out.printf("""
                    (%s) %s
                        reply: %s\n
                    """, String.valueOf(displayNum++), enquiry.getContent(), enquiry.getReplies());
        }

        System.out.printf("(%s) Exit\n", String.valueOf(displayNum));

        // prompting input
        int selection = GetInt.getInt("Select enquiry to edit:");
        while (selection <= 0 || selection > displayNum) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("Select enquiry to edit:");
        }
        if (selection == displayNum) {
            return;
        }

        String updatedContent = GetString.getString("Enter your new message:");
        Enquiry enquiry = enquiryList.get(selection - 1);
        enquiry.setContent(updatedContent);
        System.out.println("Succesfully updated enquiry\n");

    }

    @Override
    /**
     * for students to delete enquiry
     * 
     * @param camp camp to delete enquiry
     */
    public void deleteEnquiry(Camp camp) {

        /**
         * displayNum is an integer to store the display of the index of options
         */
        int displayNum = 1;
        /**
         * enquiryList is an arraylist to store the enquiries created
         */
        ArrayList<Enquiry> enquiryList = new ArrayList<>();

        // print out the content for each enquiry associated with the student
        for (Enquiry enquiry : camp.getEnquiryList()) {
            if (enquiry.getStudent() == this && !enquiry.isReplied()) {
                enquiryList.add(enquiry);

            }
        }

        if (enquiryList.size() == 0) {
            System.out.println("No deletable enquiries");
            return;
        }

        System.out.println("The list of enquiries created:");
        for (Enquiry enquiry : enquiryList) {
            System.out.printf("""
                    (%s) %s
                    reply: %s\n
                    """, String.valueOf(displayNum++), enquiry.getContent(), enquiry.getReplies());
        }

        System.out.printf("(%s) Exit\n", String.valueOf(displayNum));

        // prompting input
        int selection = GetInt.getInt("Select enquiry to delete:");
        while (selection <= 0 || selection > displayNum) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("Select enquiry to delete:");
        }
        if (selection == displayNum)
            return;
        camp.setEnquiryList(true, enquiryList.get(selection - 1));

    }

    /**
     * for students to withdraw from camp, blacklist them if they do withdraw. If
     * camp committee, unable to withdraw
     * 
     * @param camp camp to withdraw from
     */
    public void withdrawFromCamp(Camp camp) {
        // check committee
        if (camp.getCampCommittee().contains(this)) {
            System.out.println("Committee Members are not allowed to withdraw from camp");
            return;
        } else {
            camp.removeStudent(this);
            System.out.println("Withdrawn from the camp, you are added to the blacklist.");
            return;
        }
    }

    /**
     * Utility function to check if the camp is eligible for registration, and date
     * does not clash
     * 
     * @param camp             camp to check
     * @param checkFacultyOnly true if only checking for faculty, false if checking
     *                         for other conditions
     */
    public boolean isEligibleCamp(Camp camp, boolean checkFacultyOnly) { // does not check for isCommittee
        if (!checkFacultyOnly) {
            return camp.getCampVisibility()
                    && (camp.getCampFaculty() == this.facultyInformation
                            || camp.getCampFaculty() == FacultyInformation.NTU)
                    && this.registeredCampAsCommittee != camp
                    && (this.getRegisteredCampAsCommittee() == null
                            || !CampDateClash.campDateClash(camp, this.getRegisteredCampAsCommittee()))
                    && (LocalDate.now().isBefore(camp.getRegistrationClosingDate())
                            || LocalDate.now().isEqual(camp.getRegistrationClosingDate()))
                    && !CampDateClash.studentCampDateClash(camp, this.registeredCamp)
                    && (camp.getAttendeeList().size() < camp.getTotalSlots() - camp.getCampCommitteeSlots()
                            || camp.getCampCommittee().size() < camp.getCampCommitteeSlots());

        } else {
            return (camp.getCampFaculty() == this.facultyInformation || camp.getCampFaculty() == FacultyInformation.NTU)
                    && camp.getCampVisibility();
        }

    }

    /**
     * Utility function to check if the student is eligible to register as a camp
     * committee
     * 
     * @param camp camp to check
     * @return true if eligible, false if not
     */
    public boolean isEligibleAsCampCommittee(Camp camp) {
        if (isACampCommittee) {
            System.out.println("You cannot register as committee for more than a camp");
            return false;
        }
        if (camp.getCampCommittee().size() == camp.getCampCommitteeSlots()) {
            System.out.println("The committee slots is full");
            return false;
        }
        if (this.registeredCamp.contains(camp)) {
            System.out.println("you have registered this camp as attendee");
            return false;
        }
        return true;
    }

    /**
     * Utility function to print camp details
     * 
     * @param camp camp to print details
     */
    public void printCampDetails(Camp camp) {
        System.out.println("Camp Name: " + camp.getCampName());
        System.out.println("Camp Starting Date: " + String.valueOf(camp.getCampStartingDate()));
        System.out.println("Camp Ending Date: " + String.valueOf(camp.getCampEndingDate()));
        System.out.println("Camp Registration Closing Date: " + String.valueOf(camp.getRegistrationClosingDate()));
        System.out.println("Camp Faculty: " + String.valueOf(camp.getCampFaculty()));
        System.out.println("Camp Location: " + camp.getLocation());
        System.out.println("Camp Total Slots: " + String.valueOf(camp.getTotalSlots()));
        System.out.println("Camp Committee Slots: " + String.valueOf(camp.getCampCommitteeSlots()));
        System.out.println("Camp Description: " + camp.getDescription());
        System.out.println("Camp Staff In Charge: " + camp.getStaffInCharge());
    }

    /**
     * Utility function to print personal details
     */
    public void printPersonalDetails() {
        System.out.printf("""
                    name: %s
                    faculty: %s
                    ID: %s
                """, this.name, String.valueOf(this.facultyInformation), this.UserID);
        System.out.println("Camps:");
        for (Camp camp : registeredCamp) {
            System.out.println(camp.getCampName());
        }
    }
}