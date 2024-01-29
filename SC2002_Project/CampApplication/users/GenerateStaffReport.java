package CampApplication.users;
/**
 * An interface for Staff to generating reports
 * Have methods such as Generating report of the camp and generate enquiry report of the camp etc
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public interface GenerateStaffReport {
    /**
     * staff can implement this interface and create a report for the camps that they created 
     * contains the faculty of the camp, camp name, camp attendees and campcommitees
     * @param filterCampName filter by camp name
     * @param filterAttendeeName filter by an individual attendee name
     * @param filterLocation filter by camp location
     */
    public void generateReport(String filterCampName, String filterAttendeeName, String filterLocation);
    /**
     * staff can create an enquiryReport for all the camps that they create 
     * contains Camp faculty, camp name alongside the names of the student with their enquiry and replies to the enquiry if any
     */
    public void generateEnquiryReport();
}
