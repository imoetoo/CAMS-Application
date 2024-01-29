package CampApplication.users;
/**
 * An interface forCamp Committee generating reports
 * Have methods such as Generating report of the camp and generate enquiry report of the camp etc
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface GenerateStudentReport {
    /**
     * Camp committee can implement this interface to generate a report on the camp they oversee
     * @param filterAttendees Allow CampCommitee to generate report based on attendees
     * @param filterCommittees Allow CampCommitee to generate report based on attendees
     */
    public void generateReport(Boolean filterAttendees, Boolean filterCommittees);
    /**
     * Camp committe can generate an enquiry report on they camp they oversee to see 
     * which students has enquiry and whether their enquiry has been replied
     */
    public void generateEnquiryReport();
}
