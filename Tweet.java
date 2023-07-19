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

import java.util.Calendar;
import java.util.Date;

/**
 * This data type models a tweet - a short text post made on the social media service Twitter
 */
public class Tweet {

    // data fields
    public static Calendar dateGenerator;// A shared Calendar object for this class to use to generate consistent dates
    private User user; //The User associated with this tweet
    private String text; //The text of this tweet
    private int numLikes; //The number of likes this tweet has
    private int numRetweets; //The number of retweets this tweet has
    private Date timestamp; //The date and time this tweet was posted
    private static final int MAX_LENGTH = 280; //A value determining the maximum length of a tweet.

    //constructor

    /**
     * Creates a fresh, new tweet by the given user. This tweet has no likes or retweets yet, and
     * its timestamp should be set to the current time.
     *
     * @param user the User posting this tweet
     * @param text the text of the tweet
     * @throws IllegalArgumentException if the tweet's text exceeds MAX_LENGTH characters
     * @throws NullPointerException     if the provided text or user are null
     * @throws IllegalStateException    if the dateGenerator field has not yet been initialized
     */
    public Tweet(User user, String text) throws IllegalArgumentException, NullPointerException,
            IllegalStateException {
        if (text.length() > MAX_LENGTH) { //if the tweet's text exceeds MAX_LENGTH characters
            throw new IllegalArgumentException("Tweet more than 280 " + "Characters");
        }
        if (user == null || text == null) { //if the provided text or user are null
            throw new NullPointerException("Either user or Tweet is " + "null");
        }
        if (dateGenerator == null) { //if the dateGenerator field has not yet been initialized
            throw new IllegalStateException("dateGenerator not yet " + "initialized");
        }
        this.user = user;
        this.text = text;
        timestamp = dateGenerator.getTime(); //gets a calender date which is required as a consistent date
    }

    // methods

    /**
     * Initializes the dateGenerator static field to the provided Calendar object.
     *
     * @param c the Calendar to use for date generation for this run of the program
     */
    public static void setCalendar(Calendar c) {
        dateGenerator = c; //initializes the dateGenerator static field to the provided Calendar object
    }

    /**
     * Accesses the text of this tweet
     *
     * @return the text of this tweet
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the total engagement numbers (likes + retweets) of this tweet
     *
     * @return the total engagement of this tweet
     */
    public int getTotalEngagement() {
        return (numLikes + numRetweets); //calculates and returns the total engagement numbers
    }

    /**
     * Checks whether the author of this tweet is a verified user
     *
     * @return true if this tweet's User is verified, false otherwise
     */
    public boolean isUserVerified() {
        return user.isVerified(); //calls the isVerified() method
    }

    /**
     * Returns the proportion of the total engagement that is composed of "likes".
     *
     * @return the ratio of likes to total engagement , as a value between 0.0 and 1.0, or -1 if
     * the total engagement is 0.
     */
    public double getLikesRatio() {
        if (getTotalEngagement() == 0) return -1;
        return (double) numLikes / getTotalEngagement(); //returns the ratio of likes to the total engagement
    }

    /**
     * Add one (1) to the number of likes for this tweet
     */
    public void like() {
        numLikes++; //increments by one
    }

    /**
     * Add one (1) to the number of retweets for this tweet
     */
    public void retweet() {
        numRetweets++; //increments by one
    }

    /**
     * Compares the contents of this tweet to the provided object. If the provided object is a Tweet
     * that contains the same text posted at the same time by the same User
     * (use the toString() method from User to compare these!) then the two Tweets are considered
     * equal regardless of their respective likes/retweets.
     *
     * @param o the object to compare this Tweet to
     * @return true if this Tweet is equivalent to the provided object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Tweet) { //checks whether o is an instance of tweer
            Tweet twt = (Tweet) o; //explicit cast

            if ((this.user == twt.user) && (this.text.equals(twt.text)) && (this.timestamp.equals(twt.timestamp))) {
                return true; //if tweet is equivalent to the provided object
            } else {
                return false;//if tweet is not equivalent to the provided object
            }
        }
        return false;//if tweet is not equivalent to the provided object
    }

    /**
     * A string representation of this tweet.
     *
     * @return a formatted string representation of this tweet
     */
    @Override
    public String toString() {
        String outputString = "tweet from " + user.toString() + " at " + timestamp + ":" + "\n" + "--" +
                " " + text + "\n" + "-- " + "likes: " + numLikes + "\n" + "-- " + "retweets: " +
                numRetweets; //forms the appropriate the string representation

        return outputString; //returns the string representation
    }
}

