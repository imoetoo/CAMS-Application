package CampApplication.users;

import CampApplication.Utilities.*;
/**
 * User class is the parent class for all users in the system including Staff, Committee, and attendee
 * @author group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class User implements UserAccount {
    /**
     * name of the user
     */
    protected String name = "no name is assigned to user";
    /**
     * password of the user
     */
    protected String password = "password";
    /**
     * email of the user
     */
    protected String email = "";
    /**
     * faculty of the user
     */
    protected FacultyInformation facultyInformation;
    /**
     * user ID of the user
     */
    protected String UserID;
    /**
     * user details of the user (staff/student)
     */
    protected UserDetails userDetails;

    /**
     * user constructor
     * @param name name of the user
     * @param email email of the user
     * @param faculty faculty of the user
     * @param UserID user ID of the user
     * @param password password of the user
     */
    public User(String name, String email, String faculty, String UserID, String password) {
        this.name = name;
        this.email = email;
        this.facultyInformation = FacultyInformation.valueOf(faculty.toUpperCase()); // Convert and ensure it's in
                                                                                     // uppercase
        this.UserID = UserID;
    }

    /**
     * setter method for faculty information
     * @param faculty
     */
    public void setFacultyInformation(String faculty) {
        // this.facultyInformation = FacultyInformation.valueOf(faculty.toUpperCase());
        // // Convert and ensure it's in uppercase
        this.facultyInformation = FacultyInformation.valueOf(faculty.toUpperCase()); // Convert and ensure it's in
    }

    /**
     * getter method for user details
     * @return name of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter method for email
     * @return email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * getter method for UserId
     * @return user ID of the user
     */
    public String getUserId() {
        return this.UserID;
    }

    /**
     * getter method for faculty information
     * @return faculty information of the user
     */
    public FacultyInformation getFacultyInformation() {
        return this.facultyInformation;
    }

    /**
     * getter method for password
     * @return password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * getter method for user details
     * @return user details of the user
     */
    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    /**
     * setter method for user details
     * @param userID user ID of the user
     */
    public void setUserID(String userID) {
        this.UserID = userID;
    }

    /**
     * setter method for password
     * @param password password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * setter method for name
     * @param name name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * method to change password
     */
    public void changePassword() {
        this.setPassword(GetString.getString("Enter new password:"));
        System.out.println("Password changed successfully!");
    }

}
