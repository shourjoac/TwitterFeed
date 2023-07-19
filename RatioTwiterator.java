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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator that moves through tweets in reverse chronological order by high like
 * ratio only
 */
public class RatioTwiterator implements Iterator<Tweet> {
    //data fields
    private TweetNode next;// The TweetNode containing the next tweet to be returned in the iteration
    private final double THRESHOLD;//The minimum threshold value for the ratio of likes to total engagement

    /**
     * Constructs a new twiterator at the given starting node
     *
     * @param startNode the node to begin the iteration at
     * @param THRESHOLD the minimum threshold value for the ratio of likes to total engagement,
     *                  assumed to be between 0.0 and 1.0
     */
    public RatioTwiterator(TweetNode startNode, double THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
        if (startNode != null && startNode.getTweet().getLikesRatio() >= THRESHOLD) {
            next = startNode; //if startNode is not null and if the ratio is more than or equal to threshold
        } else if (startNode != null) {
            TweetNode toBeChecked = startNode.getNext(); //gets the next node
            while (toBeChecked != null && toBeChecked.getTweet().getLikesRatio() < THRESHOLD) {
                toBeChecked = toBeChecked.getNext(); //checks the successive nodes if the ration is less than threshold
            }
            next = toBeChecked;
        }
    }

    //methods

    /**
     * Checks whether there is a next tweet to return
     *
     * @return true if there is a next tweet, false if the value of next is null
     */
    @Override
    public boolean hasNext() {
        if (next == null) {
            return false; //returns false if the value of next is null
        }
        while (next.getTweet().getLikesRatio() < THRESHOLD) {
            if (!hasNextHelper(next.getNext())) {
                next = next.getNext(); //checks the successive nodes
            } else {
                return false;//returns false if there is no next tweet
            }
        }
        return true;//returns true if there is a next tweet
    }

    /**
     * Verifies if the provided TweetNode is null
     *
     * @param toBeChecked the TweetNode to be checked
     * @return true if given TweetNode is null, false otherwise
     */
    private boolean hasNextHelper(TweetNode toBeChecked) {
        return toBeChecked == null; //verifies if the TweetNode is null or not
    }

    /**
     * Returns the next tweet in the iteration if one exists, and advances next to the next tweet
     * with a likes ratio in excess of the given threshold.
     *
     * @return the next tweet in the iteration
     * @throws NoSuchElementException if there is not a next tweet to return
     */
    @Override
    public Tweet next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException(); //if there is not a next tweet to return
        }
        Tweet val = next.getTweet();
        next = next.getNext();//advances to the next tweet
        return val;//the next tweet in the iteration
    }
}

