package CampApplication.users;

import CampApplication.Camps.Camp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import CampApplication.Utilities.*;

/**
 * Represents a Staff managing the camp
 * A staff can Create Camp, Generate Reports etc
 * 
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class Staff extends User
        implements CreateCamp, ViewCampStaff, GenerateStaffReport, AnswerSuggestion, ReplyEnquiry {
    Scanner sc = new Scanner(System.in);
    /**
     * assign user to STAFF enum
     */
    private UserDetails userDetails = UserDetails.STAFF;
    /**
     * Store the camps that was created by staff
     */
    private ArrayList<Camp> ownCampList = new ArrayList<>();

    /**
     * Create a Staff object with the given parameters
     * 
     * @param name     This Staff name
     * @param email    This Staff email
     * @param faculty  This Staff faculty
     * @param UserID   This Staff userID
     * @param password This Staff password
     */
    public Staff(String name, String email, String faculty, String UserID, String password) {
        super(name, email, faculty, UserID, password);
    }

    /**
     * Add camp to camplist of staff
     * 
     * @param camp Camp that has just been created by staff added to list
     */
    public void addCamp(Camp camp) { // only for debugging purposes
        ownCampList.add(camp);
    }

    /**
     * Staff can use this function to create camp for students to enroll
     */
    public Camp createCamp() {
        Scanner sc = new Scanner(System.in);
        String campName;
        do {
            campName = GetString.getString("Enter the camp name:");
            if (isCampNameTaken(campName)) {
                System.out.println("Camp name already exists. Please choose a different name.");
            }
        } while (isCampNameTaken(campName));

        LocalDate campStartingDate = GetDate.getDate("Enter the camp starting date (yyyy-mm-dd):");
        LocalDate campEndingDate = GetDate.getDate("Enter the camp ending date (yyyy-mm-dd):");
        while (campEndingDate.compareTo(campStartingDate) < 0) {
            System.out.println("Camp ending date must after the actual camp");
            campEndingDate = GetDate.getDate("Enter the camp ending date (yyyy-mm-dd):");
        }
        LocalDate registrationClosingDate = GetDate.getDate("Enter the camp registration deadline (yyyy-mm-dd):");
        while (registrationClosingDate.compareTo(campStartingDate) >= 0) {
            System.out.println("Deadline must before the actual camp");
            registrationClosingDate = GetDate.getDate("Enter the camp registration deadline (yyyy-mm-dd):");
        }

        String ntu = GetString.getString("is this an open camp?\nEnter \"y\" if yes");

        FacultyInformation facultyInformation = (ntu.equals("y")) ? FacultyInformation.NTU
                : this.getFacultyInformation();

        String location = GetString.getString("Enter the location:");

        int totalCommitteeSlots = GetInt.getInt("Enter the total slots (maximum 10) for committee:");
        while (totalCommitteeSlots > 10 || totalCommitteeSlots <= 0) {
            System.out.println("Invalid number of slots, it must between 1 and 10 inclusively");
            totalCommitteeSlots = GetInt.getInt("Enter the total slots (maximum 10) for committee:");
        }
        int totalSlots = GetInt.getInt("Enter the total slots for attendees (inclusive of committee slots):");
        while (totalSlots <= totalCommitteeSlots) {
            System.out.println("Invalid number of slots, please input positive numbers and a number larger than "
                    + String.valueOf(totalCommitteeSlots));
            totalSlots = GetInt.getInt("Enter the total slots for attendees:");
        }

        String description = GetString.getString("Enter the camp description:");

        String staffInCharge = this.getName();

        Camp camp = new Camp(campName, campStartingDate, campEndingDate, registrationClosingDate, facultyInformation,
                location,
                totalSlots, totalCommitteeSlots, description,
                staffInCharge);
        ownCampList.add(camp);

        return camp;
    }

    @Override
    /**
     * Staff can edit the details of the camp he/she create
     * @param camp camp that the staff want to edit
     */
    public Camp editCamp(Camp camp) {
        System.out.println("(1) Camp Name");
        System.out.println("(2) Camp Faculty");
        System.out.println("(3) Camp Start Date");
        System.out.println("(4) Camp Closing Date");
        System.out.println("(5) Camp Registration Closing Date");
        System.out.println("(6) Location");
        System.out.println("(7) Total Slots");
        System.out.println("(8) Camp Committee Slots");
        System.out.println("(9) Description");
        System.out.println("(10) Staff In Charge");
        System.out.println("(11) Exit");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        final int exitIndex = 11;
        while (choice != exitIndex) {
            choice = GetInt.getInt("Which attribute you want to edit?(key 11 to exit): ");
            switch (choice) {
                case 1:
                    camp.setCampName(GetString.getString("New Camp Name: "));
                    System.out.println("Successfully changed the name of camp");
                    break;
                case 2:
                    FacultyInformation newFacultyInformation = (camp.getCampFaculty() == FacultyInformation.NTU)
                            ? this.getFacultyInformation()
                            : FacultyInformation.NTU;
                    camp.setCampFaculty(newFacultyInformation);
                    System.out.println("Camp Faculty has changed to " + String.valueOf(newFacultyInformation));
                    break;
                case 3:
                    LocalDate newStartingDate = GetDate.getDate("New Camp Starting Date: ");
                    while (newStartingDate.compareTo(camp.getCampEndingDate()) > 0) {
                        System.out.println("Starting date must before ending date");
                        newStartingDate = GetDate.getDate("New Camp Starting Date: ");
                    }
                    camp.setCampStartingDate(newStartingDate);
                    break;
                case 4:
                    LocalDate newEndingDate = GetDate.getDate("New Camp Ending Date: ");
                    while (newEndingDate.compareTo(camp.getCampStartingDate()) < 0) {
                        System.out.println("Ending date must after starting date");
                        newEndingDate = GetDate.getDate("New Camp Ending Date: ");
                    }
                    camp.setCampEndingDate(newEndingDate);
                    break;
                case 5:
                    LocalDate newRegistrationClosingDate = GetDate
                            .getDate("New camp Registration Dateline (yyyy-mm-dd):");
                    while (newRegistrationClosingDate.compareTo(camp.getRegistrationClosingDate()) >= 0) {
                        System.out.println("Deadline must before the actual camp");
                        newRegistrationClosingDate = GetDate
                                .getDate("Enter the new camp registration deadline (yyyy-mm-dd):");
                    }
                    camp.setRegistrationClosingDate(newRegistrationClosingDate);
                    break;
                case 6:
                    camp.setLocation(GetString.getString("New Location: "));
                    break;
                case 7:
                    int newTotalSlots = GetInt.getInt("New Total Slots: ");
                    while (newTotalSlots <= 0) {
                        System.out.println("Invalid range of slots. Please try again.");
                        newTotalSlots = GetInt.getInt("New Total Slots: ");
                    }
                    camp.setTotalSlots(newTotalSlots);
                    break;
                case 8:
                    int newTotalCommitteeSlots = GetInt.getInt("New Camp Committee Slots: ");
                    while (newTotalCommitteeSlots <= 0 || newTotalCommitteeSlots > 10) {
                        System.out.println("Invalid number of slots. It must between 1 and 10 inclusively.");
                        newTotalCommitteeSlots = GetInt.getInt("New Camp Committee Slots: ");
                    }
                    camp.setCampCommitteeSlots(newTotalCommitteeSlots);
                    break;
                case 9:
                    camp.setDescription(GetString.getString("New Description: "));
                    break;
                case 10:
                    camp.setStaffInCharge(GetString.getString("New Staff In Charge: "));
                    break;
                case exitIndex:
                    break;
                default:
                    System.out.println("Please enter a valid option!");
            }
        }

        return camp;

    }

    /**
     * Staff can use this function to delete a camp that they created
     * 
     * @param camp camp that they want to delete
     */
    @Override
    public void deleteCamp(Camp camp) {
        if (camp != null) {
            camp.setCampName(null);
            camp.setCampFaculty(null);
            camp.setCampStartingDate(null);
            camp.setCampEndingDate(null);
            camp.setRegistrationClosingDate(null);
            camp.setLocation(null);
            camp.setTotalSlots(0);
            camp.setCampCommitteeSlots(0);
            camp.setDescription(null);
            camp.setStaffInCharge(null);
            System.out.println("Camp is deleted successfully!");
        } else
            System.out.println("Camp doesn't exist!");
    }

    /**
     * Staff can view all camps available
     * 
     * @param campList All current camps
     * @param sc get input
     */
    @Override
    public void viewCamp(ArrayList<Camp> campList, Scanner sc) {
        int size = campList.size();
        if (size == 0) {
            System.out.println("No current camps");
            return;
        }
        Camp[] tempCampList = campList.toArray(new Camp[size]);
        String sortByDate = GetString.getString("Do you want to sort by date? (enter y if yes)");
        if (sortByDate.equals("y")) {
            CampMergeSort.mergeSortDate(tempCampList);
        } else {
            CampMergeSort.mergeSortAlphabet(tempCampList);
        }

        int displayNum = 1;
        System.out.println("Camps currently available:");
        for (Camp camp : tempCampList) {
            System.out.printf("""
                    (%s) %s
                        Staff: %s
                        Starting Date: %s
                        Attendee: (%s/%s)
                        Committee: (%s/%s)
                    """, String.valueOf(displayNum++), camp.getCampName(), camp.getStaffInCharge(),
                    String.valueOf(camp.getCampStartingDate()),
                    String.valueOf(camp.getAttendeeList().size()),
                    String.valueOf(camp.getTotalSlots() - camp.getCampCommitteeSlots()),
                    String.valueOf(camp.getCampCommittee().size()), String.valueOf(camp.getCampCommitteeSlots()));
        }
        System.out.println("Press enter to exit");
        sc.nextLine();
        return;
    }

    /**
     * Staff can view call camps that they created
     */
    public void viewOwnCamp() {
        int size = ownCampList.size();
        if (size == 0) {
            System.out.println("No current camps");
            return;
        }
        Camp[] tempCampList = ownCampList.toArray(new Camp[size]);
        String sortByDate = GetString.getString("Do you want to sort by date? (enter y if yes)");
        if (sortByDate.equals("y")) {
            CampMergeSort.mergeSortDate(tempCampList);
        } else {
            CampMergeSort.mergeSortAlphabet(tempCampList);
        }

        int displayNum = 1;
        if (ownCampList.size() == 0) {
            System.out.println("No camps under current staff");
            return;
        }
        System.out.println("Camps under current staff:");
        for (Camp camp : tempCampList) {
            System.out.printf("""
                    (%s) %s
                        Attendee: (%s/%s)
                        Committee: (%s/%s)
                    """, String.valueOf(displayNum++), camp.getCampName(),
                    String.valueOf(camp.getAttendeeList().size()),
                    String.valueOf(camp.getTotalSlots() - camp.getCampCommitteeSlots()),
                    String.valueOf(camp.getCampCommittee().size()), String.valueOf(camp.getCampCommitteeSlots()));
        }
        System.out.printf("(%s) Exit\n", String.valueOf(displayNum));

        // prompting input
        int selection = GetInt.getInt("\nSelect camp to view:");
        while (selection <= 0 || selection > displayNum) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("Select camp to view:");
        }
        if (selection == displayNum) {
            return;
        }
        Camp camp = tempCampList[selection - 1];
        CampMenuStaff.campMenuStaff(camp, this);
        return;
    }

    /**
     * Staff can use this function to view enquiries submitted to the camp they
     * created
     * 
     * @param camp the camp they want to view
     */
    @Override
    public void viewEnquiry(Camp camp) {
        ArrayList<Enquiry> enquiryList = camp.getEnquiryList();
        int displayNum = 1;
        boolean showRepliedMessage = false;

        if (GetString.getString("would you like to display replied messages? (y/n)") == "y")
            showRepliedMessage = true;

        System.out.println("The list of enquiries:");

        // print out the content for each enquiry associated with the camp
        for (Enquiry enquiry : enquiryList) {
            if (showRepliedMessage || !enquiry.isReplied()) {
                System.out.printf("""
                            (%s) Camp: %s
                                %s: %s
                                reply: %s
                        """, String.valueOf(displayNum++), enquiry.getCamp().getCampName(),
                        enquiry.getStudent().getUserId(), enquiry.getContent(), enquiry.getReplies());
                if (showRepliedMessage || enquiry.isReplied()) {
                    System.out.printf("    reply: %s\n\n", enquiry.getReplies());
                } else {
                    System.out.println("");
                }
            }
        }
    }

    /**
     * Staff can use this function to reply enquiries to camps they created
     * 
     * @param camp the camp they want to reply enquiries for
     */
    @Override
    public void replyEnquiry(Camp camp) {
        ArrayList<Enquiry> enquiryList = camp.getEnquiryList();
        System.out.println("The list of enquiries pending to be replied:");
        int displayNum = 1;

        // print out the content for each enquiry associated with the student
        boolean noReplyMessage = true;
        for (Enquiry enquiry : enquiryList) {
            if (!enquiry.isReplied()) {
                noReplyMessage = false;
                System.out.printf("""
                            (%s) Camp: %s
                                %s: %s
                                reply: %s\n
                        """, String.valueOf(displayNum++), enquiry.getCamp().getCampName(),
                        enquiry.getStudent().getUserId(), enquiry.getContent(), enquiry.getReplies());
            }
        }
        if (noReplyMessage) {
            System.out.println("No message can be replied");
            return;
        }

        System.out.printf("    (%s) Exit\n", String.valueOf(displayNum));

        // prompting input
        int selection = GetInt.getInt("Select enquiry to reply:");
        while (selection <= 0 || selection > displayNum) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("Select enquiry to reply:");
        }
        if (selection == displayNum)
            return;
        String updatedContent = GetString.getString("Enter your reply message:");
        Enquiry enquiry = enquiryList.get(selection - 1);
        enquiry.setReplies(updatedContent); // student enquiry become gone
    }

    /**
     * Staff can view suggestions by camp commitee of camps they created
     * 
     * @param camp the camp staff want to view
     */
    public void viewSuggestion(Camp camp) {

        int displayNum = 1;

        // print out the content for each enquiry associated with the camp
        for (Suggestion suggestion : camp.getSuggestionList()) {
            if (suggestion.getStatus() == Status.INPROGRESS) {
                System.out.printf("""
                            (%s) Camp: %s
                                %s:
                                %s
                        """, String.valueOf(displayNum++), suggestion.getCamp().getCampName(),
                        suggestion.getCampCommittee().getUserId(), suggestion.getContent());
            }
        }
        System.out.printf("Press enter to exit");
        sc.nextLine();
    }

    /**
     * Staff can approve suggestions by camp committee of camps they created
     * 
     * @param camp the camp staff want to view
     */
    public void approveSuggestion(Camp camp) {
        ArrayList<Suggestion> suggestionList = new ArrayList<>();
        for (Suggestion suggestion : camp.getSuggestionList()) {
            if (!suggestion.isAnswered()) {
                suggestionList.add(suggestion);
            }
        }
        int index = 1;
        for (Suggestion suggestion : suggestionList) {
            System.out.printf("""
                        (%s) Camp: %s
                            %s:
                            %s
                    """, String.valueOf(index++), suggestion.getCamp().getCampName(),
                    suggestion.getCampCommittee().getUserId(), suggestion.getContent());
        }
        if (suggestionList.size() == 0) {
            System.out.println("No suggestion is submitted");
            return;
        }

        System.out.printf("    (%s) Exit\n", String.valueOf(index));

        // prompting input
        int selection = GetInt.getInt("select suggestion to reply:");
        while (selection <= 0 || selection > index) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("select suggestion to reply:");
        }
        if (selection == index) {
            return;
        }

        // make changes
        int approved = GetInt.getInt("Do you want to approve this suggestion? (1=approve, 2=reject)");
        while (approved != 1 && approved != 2) {
            System.out.println("Invalid input. Please try again");
            approved = GetInt.getInt("Do you want to approve this suggestion? (1=approve, 2=reject)");
        }
        Suggestion suggestion = suggestionList.get(selection - 1);
        suggestion.setAnswered();
        switch (approved) {
            case 1:
                suggestion.setStatus(Status.APPROVED);
                suggestion.getCampCommittee().addPoint();
                break;
            case 2:
                suggestion.setStatus(Status.REJECTED);
                break;
            default:
                break;
        }
        System.out.println("Suggestion status updated.");
    }

    /**
     * Staff can view suggestions by camp commitee of camps they created
     */
    public void getOwnCamplist() {
        boolean found = false;
        for (Camp camp : ownCampList) {
            if (camp.getStaffInCharge() == getName()) {
                found = true;
                System.out.println(camp.getCampName());
            }
        }
        if (!found) {
            System.out.println("You have not created any camps.");
        }
    }

    /**
     * generate performance report of camp committees
     * @param camp the associated camp that the staff want to generate report for
     */
    // Generate Reports
    public void generatePerformanceReport(Camp camp) {
        for (CampCommittee comp : camp.getCampCommittee()) {
            System.out.println("Committee member " + comp.getName() + " has obtained " + comp.getPoints());
        }
    }

    /**
     * staff can create a report for the camps that they created
     * contains the faculty of the camp, camp name, camp attendees and campcommitees
     * 
     * @param filterCampName     filter by camp name
     * @param filterAttendeeName filter by an individual attendee name
     * @param filterLocation     filter by camp location
     */
    @Override
    public void generateReport(String filterCampName, String filterAttendeeName, String filterLocation) {
        try {
            File myObj = new File("CampList.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                System.out.println("Here is the report.");
            } else {
                System.out.println("File already exists.");
                System.out.println("Here is the updated report.");
            }

            // Open the file for writing
            FileWriter fileWriter = new FileWriter(myObj, false);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            // Write camp information for each camp in the Camps list
            if (filterCampName == null && filterAttendeeName == null && filterLocation == null)
                for (Camp camp : ownCampList) {
                    // Check if the camp matches the specified filters or generate the full report
                    bw.write("Faculty of Camp: " + camp.getCampFaculty());
                    bw.newLine();
                    bw.write("Camp Name: " + camp.getCampName());
                    bw.newLine();
                    // Append attendees' names
                    bw.write("Camp Attendees: ");
                    for (Student student : camp.getAttendeeList()) {
                        bw.write(student.getName() + " ");
                    }
                    bw.newLine();
                    // Append committee members' names
                    bw.write("Camp Committees: ");
                    for (Student student : camp.getCampCommittee()) {
                        bw.write(student.getName() + " ");
                    }
                    bw.newLine();
                    // Move to the next line for the next camp or information
                    bw.newLine();
                }
            else if (filterCampName != null) {
                for (Camp camp : ownCampList) {
                    if (camp.getCampName().equals(filterCampName)) {
                        bw.write("Faculty of Camp: " + camp.getCampFaculty());
                        bw.newLine();
                        bw.write("Camp Name: " + camp.getCampName());
                        bw.newLine();

                        // Append attendees' names
                        bw.write("Camp Attendees: ");
                        for (Student student : camp.getAttendeeList()) {
                            bw.write(student.getName() + " ");
                        }
                        bw.newLine();

                        // Append committee members' names
                        bw.write("Camp Committees: ");
                        for (Student student : camp.getCampCommittee()) {
                            bw.write(student.getName() + " ");
                        }
                        bw.newLine();
                        // Move to the next line for the next camp or information
                        bw.newLine();
                    }
                }
            } else if (filterAttendeeName != null) {
                for (Camp camp : ownCampList) {
                    for (int i = 0; i < camp.getAttendeeList().size(); i++) {
                        String c = (camp.getAttendeeList().get(i).getName()).trim();
                        if (c.equals(filterAttendeeName)) {
                            bw.write("Faculty of Camp: " + camp.getCampFaculty());
                            bw.newLine();
                            bw.write("Camp Name: " + camp.getCampName());
                            bw.newLine();
                            bw.write("Camp Attendees: ");
                            bw.write(filterAttendeeName);
                            bw.newLine();
                            // Append committee members' names
                            bw.write("Camp Committees: ");
                            for (Student student2 : camp.getCampCommittee()) {
                                bw.write(student2.getName() + " ");
                            }
                            bw.newLine();
                            // Move to the next line for the next camp or information
                            bw.newLine();
                        }
                    }
                }
            }

            else {
                for (Camp camp : ownCampList) {
                    if (camp.getCampLocation().equals(filterLocation)) {
                        bw.write("Faculty of Camp: " + camp.getCampFaculty());
                        bw.newLine();
                        bw.write("Camp Name: " + camp.getCampName());
                        bw.newLine();

                        // Append attendees' names
                        bw.write("Camp Attendees: ");
                        for (Student student : camp.getAttendeeList()) {
                            bw.write(student.getName() + " ");
                        }
                        bw.newLine();
                        // Append committee members' names
                        bw.write("Camp Committees: ");
                        for (Student student : camp.getCampCommittee()) {
                            bw.write(student.getName() + " ");
                        }
                        bw.newLine();
                        // Move to the next line for the next camp or information
                        bw.newLine();
                    }
                }
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("No report to generate.");
        }
    }

    /**
     * staff can create an enquiryReport for all the camps that they create
     * contains Camp faculty, camp name alongside the names of the student with
     * their enquiry and replies to the enquiry if any
     */
    @Override
    public void generateEnquiryReport() {
        try {
            File myObj = new File("CampEnquiriesList.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                System.out.println("Here is the report.");
            } else {
                System.out.println("File already exists.");
                System.out.println("Here is the updated Enquiry report.");
            }

            // Open the file for writing
            FileWriter fileWriter = new FileWriter(myObj, false);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            // Write camp information for each camp in the Camps list
            for (Camp camp : ownCampList) {
                bw.write(" Faculty of Camp is: " + camp.getCampFaculty());
                bw.newLine();
                bw.write(" Camp Name: " + camp.getCampName());
                bw.newLine();
                // Append attendees' names
                bw.write(" Student Camp Enquiries: ");
                for (Enquiry enq : camp.getEnquiryList()) {
                    bw.newLine();
                    bw.write("  " + enq.getStudent().getName());
                    bw.write(": " + enq.getContent() + " ");
                    bw.write(": " + enq.getReplies() + " ");

                }
                bw.newLine(); // Move to the next line for the next camp or information
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("No report to generate.");
        }

    }

    /**
     * check if there is clash between camp names
     * 
     * @param campName individual camp name
     * @return return true/false depending if camp name taken
     */
    private boolean isCampNameTaken(String campName) {
        // Check if the camp name is already taken
        for (Camp existingCamp : ownCampList) {
            if (existingCamp.getCampName().equals(campName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * print camp details
     * 
     * @param camp the chosen camp
     */
    public void printCampDetails(Camp camp) {
        camp.printCampDetails(false);
    }

    public void StudentList() {
        System.out.println(Student.getListofStudents());
    }

    // for debug use
    public void printPersonalDetails() {
        System.out.printf("""
                    name: %s
                    faculty: %s
                    ID: %s
                """, this.name, String.valueOf(this.facultyInformation), this.UserID);
        System.out.println("Camps:");
        for (Camp camp : ownCampList) {
            System.out.println(camp.getCampName());
        }
    }

}
