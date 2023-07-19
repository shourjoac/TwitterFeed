//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Twitter Feed
// Course:   CS 300 Spring 2023
//
// Author:   Shourjo Aditya Chaudhuri
// Email:    sachaudhuri@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner) None
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a Twitter user.
 */
public class User {

    // data fields
    private boolean isVerified; //The verification status of the user
    // (whether they have a blue checkmark or not)
    private String username; //The username this User tweets under
    //constructor

    /**
     * Constructs a new User object with a given username. All Users are unverified by default.
     *
     * @param username the username of this User
     * @throws IllegalArgumentException if the given username contains an asterisk ("*") character,
     *                                  or is null, or is blank
     */
    public User(String username) throws IllegalArgumentException {
        if (username.contains("*") || username == null || username.equals("")) { //checks the conditions for exception
            throw new IllegalArgumentException("Invalid username"); //throws exception with an appropriate message
        }
        this.username = username;
    }

    // methods

    //getters

    /**
     * Accesses the username of this User
     *
     * @return the username this User tweets under
     */
    public String getUsername() {
        return username;
    }

    /**
     * Determines whether the User is a verified user or not
     *
     * @return true if this User is verified
     */
    public boolean isVerified() {
        return isVerified;
    }

    //setters

    /**
     * Gives this User an important-looking asterisk
     */
    public void verify() {
        isVerified = true;
    }

    /**
     * Takes this User's important-looking asterisk away
     */
    public void revokeVerification() {
        isVerified = false;
    }

    /**
     * Creates a String representation of this User for display.
     * <p>
     * For example, if this User's username is "uwmadison" and they are verified, this method will
     * return "@uwmadison*"; if this User's username is "dril" and they are not verified, this
     * method will return "@dril" (with no asterisk).
     * <p>
     *
     * @return a String representation of this User as described above
     */
    @Override
    public String toString() {
        String outputString = "";
        if (isVerified) {
            outputString = "@" + username + "*"; // Adds Asterisk if the user is verified
        } else {
            outputString = "@" + username; // Does not add Asterisk if the user is verified
        }
        return outputString;
    }

}
