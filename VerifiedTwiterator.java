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
 * This is an iterator that moves through tweets in reverse chronological order by verified users
 * only.
 *
 * @author Shourjo Aditya Chaudhuri
 */
public class VerifiedTwiterator implements Iterator<Tweet> {
    //data fields
    private TweetNode next;//The TweetNode containing the next tweet to be returned in the iteration

    //constructor

    /**
     * Constructs a new twiterator at the given starting node
     *
     * @param startNode the node to begin the iteration at
     */
    public VerifiedTwiterator(TweetNode startNode) {
        if (startNode.getTweet().isUserVerified()) { //obtains the tweet at the startNode and checks if the user is
            //verified
            next = startNode;
        } else {
            TweetNode toBeChecked = startNode.getNext(); //checks the node after the startNode
            while (!toBeChecked.getTweet().isUserVerified()) {
                toBeChecked = toBeChecked.getNext(); //checks the subsequent nodes
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
            return false; //if next is null the method returns false
        }
        while (!next.getTweet().isUserVerified()) {
            if (!hasNextHelper(next.getNext())) {
                next = next.getNext(); //checks the next nodes
            } else {
                return false; //if there is no next tweet
            }
        }
        return true; //if there is a next tweet
    }

    /**
     * Verifies if the provided TweetNode is null
     *
     * @param toBeChecked the TweetNode to be checked
     * @return true if given TweetNode is null, false otherwise
     */
    private boolean hasNextHelper(TweetNode toBeChecked) {
        return toBeChecked == null; //verifies if given TweetNode is null
    }

    /**
     * Returns the next tweet in the iteration if one exists, and advances next to the next tweet
     * by a verified user.
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
        next = next.getNext(); //advances to the next tweet
        return val;
    }
}
