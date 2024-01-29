package CampApplication.users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import CampApplication.Camps.Camp;
import CampApplication.Utilities.GetInt;
import CampApplication.Utilities.GetString;

/**
 * Represents a Camp Commitee object that manage the camp they are a commitee of
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class CampCommittee extends Student implements GenerateStudentReport, ReplyEnquiry, CreateSuggestion {
    // camp object the committee belongs to
    private Camp camp;
    // student object the committee specialized from
    private Student student;
    // list of suggestions the committee made
    public ArrayList<Suggestion> suggestionsMade = new ArrayList<>();
    // points accumulated by the committee
    private int points = 0;

    /**
     * constructor to initiate committee object
     * 
     * @param name     name of committee
     * @param email    email of committee
     * @param faculty  faculty information of committee
     * @param UserID   userID of committee
     * @param password password of committee
     * @param camp     camp object the committee under
     * @param student  student object the committee specialized from
     */
    public CampCommittee(String name, String email, String faculty, String UserID, String password, Camp camp,
            Student student) {
        super(name, email, faculty, UserID, password);
        this.camp = camp;
        // this.student = student;
    }

    // getters and setters
    /**
     * getter to access student object the committee specialized from
     * 
     * @return student object
     */
    public Student getStudent() {
        return student;
    }

    /**
     * getter to access camp object the committee under
     * 
     * @return camp object
     */
    public Camp getCamp() {
        return camp;
    }

    /**
     * setter to modify camp object
     * 
     * @param camp
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    /**
     * getter to access committee accumulated points
     * 
     * @return accumulated points
     */
    public int getPoints() {
        return points;
    }

    /**
     * setter to modify accumulated points
     * 
     * @param a
     */
    public void setPoints(int a) {
        points = a;
    }

    /**
     * function to increment committee points
     */
    public void addPoint() {
        this.points++;
    }

    /**
     * function to decrement committee points
     */
    public void deletePoint() {
        this.points--;
    }

    /**
     * Camp committee can generate an enquiry report on they camp they oversee to see
     * which students has enquiry and whether their enquiry has been replied
     */
    @Override
    public void generateEnquiryReport() {
        try {
            File myObj = new File("CommiteeCampEnquiryList.txt");
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
            bw.write("Camp Name: " + this.getCamp().getCampName());
            bw.newLine();
            bw.write("Student Camp Enquiries: ");
            for (Enquiry enq : this.getCamp().getEnquiryList()) {
                bw.newLine();
                bw.write("  " + enq.getStudent().getName());
                bw.write(": " + enq.getContent() + " ");
                bw.write(": " + enq.getReplies() + " ");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("No report to generate.");
        }
    }

    /**
     *Camp committee members are able to view their own suggestions
     *@param camp suggestion for the specific camp
     */
    // Methods - Suggestion
    @Override
    public void viewSuggestion(Camp camp) {
        int displayNum = 1;
        if (suggestionsMade.size() == 0) {
            System.out.println("no suggestion made");
            return;
        }

        System.out.println("The list of suggestions made:");

        // print out the content for each enquiry associated with the camp
        for (Suggestion suggestion : suggestionsMade) {
            System.out.printf("""
                        (%s) Camp: %s
                        %s:
                        %s
                        Status: %s\n\n
                    """, String.valueOf(displayNum++), suggestion.getCamp().getCampName(),
                    suggestion.getCampCommittee().getUserId(), suggestion.getContent(),
                    String.valueOf(suggestion.getStatus()).toLowerCase());
        }
    }


    /**
     * Camp committee members are able to submit suggestions
     * @param camp suggestion for the specific camp
     */
    @Override
    public void submitSuggestion(Camp camp) {
        String content = GetString.getString("Enter your suggestion content:");
        Suggestion newSuggestion = camp.addNewSuggestion(content, this);
        suggestionsMade.add(newSuggestion);
        System.out.println("Suggestion succesfully added");
        addPoint();

    }

    /**
     * Camp commiteee members are able to choose which suggestion they want to edit
     * @param camp suggestion for the specific camp
     */
    @Override
    public void editSuggestion(Camp camp) {
        ArrayList<Suggestion> suggestionList = new ArrayList<>();
        for (Suggestion suggestion : suggestionsMade) {
            if (suggestion.getStatus() == Status.INPROGRESS) {
                suggestionList.add(suggestion);

            }
        }
        if (suggestionList.size() == 0) {
            System.out.println("No editable suggestion");
            return;
        }
        System.out.println("The list of suggestions made:");
        int displayNum = 1;
        for (Suggestion suggestion : suggestionList) {
            System.out.printf("""
                    (%s) %s
                    status: %s\n
                    """, String.valueOf(displayNum++), suggestion.getContent(),
                    String.valueOf(suggestion.getStatus()));
        }

        // print out the content for each enquiry associated with the student
        System.out.printf("(%s) Exit\n", String.valueOf(displayNum));

        // prompting input
        int selection = GetInt.getInt("Select suggestion to edit:");
        while (selection <= 0 || selection > displayNum) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("Select suggestion to edit:");
        }
        if (selection == displayNum) {
            return;
        }

        String outdatedContent = this.suggestionsMade.get(selection - 1).getContent();
        String updatedContent = GetString.getString("Enter your new suggestion:");
        camp.setSuggestionList(false, updatedContent, outdatedContent, null);
    }

    /**
     * Camp committee memebrs are able to choose which suggestion they want to delete
     * @param camp suggestion for the camp
     */
    @Override
    public void deleteSuggestion(Camp camp) {
        ArrayList<Suggestion> suggestionList = new ArrayList<>();
        Suggestion suggestionToBeRemoved = null;
        for (Suggestion suggestion : suggestionsMade) {
            if (suggestion.getStatus() == Status.INPROGRESS) {
                suggestionList.add(suggestion);

            }
        }
        if (suggestionList.size() == 0) {
            System.out.println("No deletable suggestion");
            return;
        }
        System.out.println("The list of suggestions made:");
        int displayNum = 1;
        for (Suggestion suggestion : suggestionList) {
            System.out.printf("""
                    (%s) %s
                    status: %s\n
                    """, String.valueOf(displayNum++), suggestion.getContent(),
                    String.valueOf(suggestion.getStatus()));
        }

        // print out the content for each enquiry associated with the student
        System.out.printf("(%s) Exit\n", String.valueOf(displayNum));

        // prompting input
        int selection = GetInt.getInt("Select suggestion to delete:");
        while (selection <= 0 || selection > displayNum) {
            System.out.println("Input out of range! Please try again.");
            selection = GetInt.getInt("Select suggestion to delete:");
        }
        if (selection == displayNum) {
            return;
        } else {
            suggestionToBeRemoved = suggestionList.get(selection - 1);
        }
        camp.setSuggestionList(true, "updatedContent", "outdatedContent", suggestionToBeRemoved);
        this.suggestionsMade.remove(suggestionToBeRemoved);
        deletePoint();
    }

    // Enquiry
    /**
     * Camp commitee members are able to view enquiries made by attendees
     * @param camp enquiry for the specific camp
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
     * Camp committee memebrs are able to reply to enquiries made by attendees
     * @param camp enquiry for the specific camp
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
        enquiry.setReplies(updatedContent);

        this.addPoint();
    }

    // generate camp report based on the camp that the student is a camp commitee of
    // list out the camp name, camp attendees and the camp committee members`
    @Override
    public void generateReport(Boolean filterAttendees, Boolean filterCommittees) {
        try {
            File myObj = new File("CommiteeCampList.txt");
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
            bw.write(" Camp Name: " + this.getCamp().getCampName());
            bw.newLine();
            if (filterAttendees == false && filterCommittees == false) {
                bw.write(" Camp Attendees: ");
                for (Student stu : this.getCamp().getAttendeeList()) {
                    bw.write(stu.getName() + " ");
                }
                bw.newLine();
                bw.write(" Camp Committees: ");
                for (CampCommittee campcomp : this.getCamp().getCampCommittee()) {
                    bw.write(campcomp.getName());
                }
            } else if (filterAttendees == true) {
                bw.write(" Camp Attendees: ");
                for (Student stu : this.getCamp().getAttendeeList()) {
                    bw.write(stu.getName() + " ");
                }
            } else {
                bw.write(" Camp Committees: ");
                for (CampCommittee campcomp : this.getCamp().getCampCommittee()) {
                    bw.write(campcomp.getName());
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("No report to generate.");
        }
    }

    @Override
    public String toString() {
        return "CampCommittee{name='" + getName() + "', email='" + getEmail() + " " +
                "password='" + getPassword() + " " + "faculty='" + getFacultyInformation() + "'}"; // Customize as
                                                                                                   // needed
    }

    /**
     * Camp committee members are able to view their own points
     */
    public void printPoints() {
        System.out.println("Points: " + String.valueOf(this.points));
    }

}
