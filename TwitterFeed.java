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
 * This class models a reverse-chronological Twitter feed using a singly-linked list.
 * By default, new tweets are added at the head of the list.
 *
 * @author Shourjo Aditya Chaudhuri
 */
public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {
    // data fields
    private TweetNode head; //The node containing the most recent tweet
    private TweetNode tail; //The node containing the oldest tweet
    private int size; //The number of tweets in this linked list
    private TimelineMode mode; //The iteration mode for the timeline display
    private static double ratio; // The ratio of likes to total engagement that we want to see; set to .5 by default

    //constructor

    /**
     * Default constructor; creates an empty TwitterFeed by setting all data fields to their
     * default values, and the timeline mode to CHRONOLOGICAL.
     */
    public TwitterFeed() {
        mode = TimelineMode.CHRONOLOGICAL;
        head = null;
        tail = null;
        ratio = 0.5;
        size = 0;
    }

    /**
     * Accessor for the size of the feed
     *
     * @return the number of TweetNodes in this TwitterFeed
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Determines whether this feed is empty.
     *
     * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
     */
    @Override
    public boolean isEmpty() {
        if (size == 0 && head == null && tail == null) {
            return true;//returns true if there are NO TweetNodes in this TwitterFeed
        }
        return false;//returns false if there are TweetNodes in this TwitterFeed
    }

    /**
     * Determines whether a given Tweet is present in the TwitterFeed.
     *
     * @param findObject the Tweet to search for
     * @return true if the Tweet is present, false otherwise
     */
    @Override
    public boolean contains(Tweet findObject) {
        TweetNode tmp = head; //initializes temp as head ( starting of the linked list)
        for (int i = 0; i < size; i++) {
            if (tmp.getTweet().equals(findObject)) {
                return true;//true if the Tweet is present
            }
            tmp = tmp.getNext(); //stores the next node
        }
        return false; //if the Tweet is absent
    }

    /**
     * Accessor method for the index of a given Tweet in the TwitterFeed.
     *
     * @param findObject the Tweet to search for
     * @return the index of the Tweet in the TwitterFeed if present,
     * -1 if not present
     */
    @Override
    public int indexOf(Tweet findObject) {
        TweetNode tmp = head; //initializes temp as head ( starting of the linked list)
        for (int i = 0; i < size; i++) { //traverses through the list
            if (tmp.getTweet().equals(findObject)) {
                return i; //returns the index of a given Tweet
            }
            tmp = tmp.getNext();//stores the next node
        }
        return -1; //if the given tweet is not present
    }

    /**
     * Accessor method for the Tweet at a given index
     *
     * @param index the index of the Tweet in question
     * @return the Tweet object at that index
     * @throws IndexOutOfBoundsException if the index is negative or greater than the largest index
     *                                   of the TwitterFeed
     */
    @Override
    public Tweet get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(); //if the index is negative or greater than the largest index
            // of the TwitterFeed
        }
        TweetNode tmp = head; //initializes temp as head ( starting of the linked list)
        for (int i = 0; i < index; i++) { //traverses through the list
            tmp = tmp.getNext(); //stores the next node
        }
        return tmp.getTweet(); //gets the required tweet and returns it
    }

    /**
     * Accessor method for the first Tweet in the TwitterFeed
     *
     * @return The Tweet object at the head of the linked list
     * null if head is null
     * @throws NoSuchElementException if the TwitterFeed is empty
     */
    public Tweet getHead() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(); //if the TwitterFeed is empty
        }
        if (this.head == null) {
            return null; //if the list is empty ie the head is null
        }
        return head.getTweet(); //The Tweet object at the head of the linked list null if head is null
    }

    /**
     * Accessor method for the last Tweet in the TwitterFeed
     *
     * @return the Tweet object at the tail of the linked list
     * @throws NoSuchElementException if the TwitterFeed is empty
     */
    public Tweet getTail() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(); //if the TwitterFeed is empty
        }
        return tail.getTweet(); //returns the Tweet object at the tail of the linked list
    }

    /**
     * Helper method to get the node at a particular index
     *
     * @param index Index of the node to be accessed
     * @return the required node at that particular index
     */
    private TweetNode getNode(int index) {
        TweetNode tmp = head; //initializes temp as head ( starting of the linked list)
        for (int i = 0; i < index; i++) { //traverses through the elements of the linked list
            tmp = tmp.getNext(); //stores the next node in temp
        }
        return tmp;
    }

    /**
     * Adds the given Tweet to the tail of the linked list
     *
     * @param newObject the Tweet to add
     */
    @Override
    public void addLast(Tweet newObject) {
        TweetNode toBeAdded = new TweetNode(newObject);// creates a new node
        if (size == 0) {
            head = toBeAdded;
            tail = toBeAdded;
        } else {
            tail.setNext(toBeAdded);
            tail = toBeAdded;
        }
        size++; // increments the size data field
    }

    /**
     * Adds the given Tweet to the head of the linked list
     *
     * @param newObject the Tweet to add
     */
    @Override
    public void addFirst(Tweet newObject) {
        TweetNode toBeAdded = new TweetNode(newObject); // creates a new node
        toBeAdded.setNext(head); // makes the new node point to the current head
        head = toBeAdded; // updates the current head
        if (size == 0) {
            tail = toBeAdded;
        }
        size++; // increments the size data field
    }

    /**
     * Adds the given Tweet to a specified position in the linked list
     *
     * @param index     the position at which to add the new Tweet
     * @param newObject the Tweet to add
     * @throws IndexOutOfBoundsException if the index is negative or greater than the size of the
     *                                   linked list
     */
    @Override
    public void add(int index, Tweet newObject) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        //adding at the beginning
        if (index == 0) {
            addFirst(newObject);
        }
        // adding at the end
        else if (index == size) {
            addLast(newObject);
        }
        // adding at the middle
        else {
            TweetNode toBeAdded = new TweetNode(newObject);// creates a new node
            TweetNode tmp = getNode(index - 1);
            toBeAdded.setNext(tmp.getNext());
            tmp.setNext(toBeAdded);
            size++;//increments the size
        }
    }

    /**
     * Removes and returns the Tweet at the given index
     *
     * @param index the position of the Tweet to remove
     * @return The Tweet object that was removed from the list
     * @throws IndexOutOfBoundsException if the index is negative or greater than the largest index
     *                                   currently present in the linked list
     */
    @Override
    public Tweet delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (size == 1) { // if there is only one element
            TweetNode toBeDeleted = head;
            head = null;
            size--;//decrements size by one
            tail = null;
            return toBeDeleted.getTweet();

        }
        if (index == 0) { // deleting from the start(head) of the list
            TweetNode toBeDeleted = head;
            head = head.getNext();
            size--;//decrements size by one
            return toBeDeleted.getTweet();
        } else if (index == size - 1) { // deleting from the end of the list
            TweetNode toBeDeleted = tail;
            TweetNode indexBeforeDelete = getNode(index - 1);
            tail = indexBeforeDelete;
            indexBeforeDelete.setNext(null);
            size--;//decrements size by one
            return toBeDeleted.getTweet();
        } else { // deleting an element from anywhere in the middle of the list
            TweetNode indexBeforeDelete = getNode(index - 1);
            TweetNode toBeDeleted = getNode(index);
            indexBeforeDelete.setNext(toBeDeleted.getNext());
            size--; //decrements size by one
            return toBeDeleted.getTweet();
        }
    }

    /**
     * Sets the iteration mode of this TwitterFeed
     *
     * @param m the iteration mode; any value from the TimelineMode enum
     */
    public void setMode(TimelineMode m) {
        mode = m;
    }

    /**
     * Creates and returns the correct type of iterator based on the current mode of this T
     * witterFeed
     *
     * @return any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator, initialized to the
     * head of this TwitterFeed list
     */
    @Override
    public Iterator<Tweet> iterator() {
        if (mode == TimelineMode.CHRONOLOGICAL)
            return new ChronoTwiterator(this.head); //returns ChronoTwiterator to the head of the TwitterFeed list

        if (mode == TimelineMode.VERIFIED_ONLY)
            return new VerifiedTwiterator(this.head); //returns VerifiedTwiterator to the head of the TwitterFeed list

        if (mode == TimelineMode.LIKE_RATIO)
            return new RatioTwiterator(this.head, ratio); //returns RatioTwiterator to the head of the TwitterFeed list

        return null;
    }
}







