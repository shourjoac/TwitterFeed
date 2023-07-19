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
import java.util.NoSuchElementException;

/**
 * This class contains various tester methods with several cases to test the other classes
 * of PO7 Twitter Feed project
 *
 * @author Shourjo Aditya Chaudhuri
 */
public class TwiteratorTester {
    public static boolean testUser() {
        Tweet.setCalendar(Calendar.getInstance());
        User user1 = new User("James");
        User user2 = new User("Kyle");
        //(1) Verify getUserName
        {
            String actual = user1.getUsername();
            String expected = "James";
            if (actual != expected) return false;
        }
        //(2) Checking isVerified for an unVerified account
        {
            boolean actual = user1.isVerified();
            boolean expected = false;
            if (actual != expected) return false;
        }
        //(3) Checking the implementation of verify method by calling isVerified for a verified user
        // after calling verify on an user object
        {
            user2.verify();
            boolean actual = user2.isVerified();
            boolean expected = true;
            if (actual != expected) return false;
        }

        //(4) Checking toString for a verified account. user1 is unverified
        {
            String actual = "@James";
            String expected = user1.toString();
            if (!actual.equals(expected)) return false;
        }

        //(5) Checking toString for a verified account. user2 is verified
        {
            String actual = "@Kyle*";
            String expected = user2.toString();
            if (!actual.equals(expected)) return false;

        }
        //(6) checking revokeVerification
        {
            // if we have gotten this far, we now know that user2 is verified. Let's revoke it
            user2.revokeVerification();
            boolean actual = user2.isVerified();
            boolean expected = false;
            if (actual != expected) return false;
        }
        //(7) Invalid Username
        {
            try {
                User user5 = new User("James*");
                return false;
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static boolean testTweet() {

        User user1 = new User("James");
        User user2 = new User("Kyle");

        //(1) check if IllegalStateException exception is thrown if dateGenerator is not
        // initialised
        try {
            Tweet tweet1 = new Tweet(user1, "Hi");
            return false;
        } catch (IllegalStateException e) {

        } catch (Exception e) {
            return false;
        }

        Tweet.setCalendar(Calendar.getInstance());
        Tweet tweet1 = new Tweet(user1, "Hi");
        Tweet tweet2 = new Tweet(user2, "How are you?");

        //(2) getText method
        {
            String actual = tweet1.getText();
            String expected = "Hi";
            if (actual != expected) return false;
        }
        //(3) verifying like, retweet, and getTotalEngagement
        {
            for (int i = 0; i < 3; ++i) { //total 6 likes and 3 retweets
                tweet1.like();
                tweet1.like();
                tweet1.retweet();
            }
            int expected = 9;
            int actual = tweet1.getTotalEngagement();
            if (actual != expected) return false;

            //(3) checking getLikesRatio
            double expected1 = (double) 2 / 3;
            double actual1 = tweet1.getLikesRatio();
            if (actual != expected) return false;

        }

        //(4) checking the equals method
        {
            // different time stamps
            Calendar test = Calendar.getInstance();
            test.set(2012, Calendar.MAY, 23, 15, 47, 55);
            Tweet.setCalendar(test);
            Tweet tweet3 = new Tweet(user1, "Hi");
            ;
            boolean actual = tweet1.equals(tweet3);
            boolean expected = false;
            if (actual != expected) return false;

            // all same and returns true
            Tweet tweet4 = new Tweet(user1, "Hi");
            boolean actual1 = tweet3.equals(tweet4);
            boolean expected1 = true;
            if (actual1 != expected1) return false;

            //different messages but same user
            Tweet tweet5 = new Tweet(user2, "Hi");
            boolean actual2 = tweet1.equals(tweet3);
            boolean expected2 = false;
            if (actual2 != expected2) return false;

            // same user but different messages
            Tweet tweet6 = new Tweet(user2, "Different Hi");
            boolean actual3 = tweet6.equals(tweet5);
            boolean expected3 = false;
            if (actual3 != expected3) return false;

            // changing the refernece type of argument
            boolean actual4 = tweet6.equals("Should return false");
            boolean expected4 = false;
            if (actual4 != expected4) return false;
        }


        //(5) Test toString implementation
        {
            Tweet tweet7 = new Tweet(user1, "Hi");
            for (int i = 0; i < 3; ++i) { //total 6 likes and 3 retweets
                tweet7.like();
                tweet7.like();
                tweet7.retweet();
            }
            String expected = "tweet from " + "@James" + " at " + "Wed May 23 15:47:55 CDT 2012" +
                    ":" + "\n" + "-- " + "Hi" + "\n" + "-- " + "likes: " + 6 + "\n" + "-- " +
                    "retweets: " + 3;
            String actual = tweet7.toString();

            if (!expected.equals(actual)) return false;

        }
        //(6) NullPointerException when user is null
        {
            try {
                Tweet tweet4 = new Tweet(null, "NullPointer Expected");
                return false;
            } catch (NullPointerException e) {

            } catch (Exception e) {
                return false;
            }
        }

        //(7) IllegalArgumentException when text is more than 280 Characters
        {
            try {
                Tweet tweet4 = new Tweet(user1, " /////////////////jjjnnnoh" +
                        ".9999300///3" +
                        "///////////////////////////////////////////////////////////////////" +
                        "/////////////////002,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,," +
                        ",,,00" + "//////////////////222////////////////////////////////////////////" +
                        "//////////////////////////rrfrewg///////////////////////////////////////////");
                return false;
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }


    public static boolean testNode() {
        Tweet.setCalendar(Calendar.getInstance());
        User user1 = new User("James");
        User user2 = new User("Kyle");
        Tweet tweet1 = new Tweet(user1, "Hi");
        Tweet tweet2 = new Tweet(user2, "How are you?");
        TweetNode node2 = new TweetNode(tweet2, null);
        TweetNode node1 = new TweetNode(tweet1, node2);


        //(1) getTweet method
        {
            Tweet expected = node1.getTweet();
            Tweet actual = tweet1;
            if (actual != expected) return false;
        }

        //(2) Testing getNext when there is a next node
        {
            TweetNode expected = node1.getNext();
            TweetNode actual = node2;
            if (expected != actual) return false;
        }

        //(3) Testing getNext when there is no next Node
        {
            TweetNode expected = node2.getNext();
            TweetNode actual = null;
            if (expected != actual) return false;
        }

        return true;
    }

    public static boolean testAddTweet() {
        Tweet.setCalendar(Calendar.getInstance());
        User user1 = new User("James");
        User user2 = new User("Kyle");
        Tweet tweet1 = new Tweet(user1, "Hi");
        Tweet tweet2 = new Tweet(user2, "How are you?");
        User user3 = new User("Francis");
        Tweet tweet3 = new Tweet(user3, "Computer Science");


        TwitterFeed test = new TwitterFeed();

        // (1) Now, size should be empty
        {
            int actual = 0;
            int expected = test.size();
            if (actual != expected) return false;

            boolean actualBoolean = true;
            boolean expectedBoolean = test.isEmpty();
            if (actualBoolean != expectedBoolean) return false;
        }

        //(2) Using addFirst - after adding we check that size has been appropriately incremented,
        // isEmpty() returns false
        {
            test.addFirst(tweet1);
            boolean actualBoolean = false;
            boolean expectedBoolean = test.isEmpty();
            if (actualBoolean != expectedBoolean) return false;

            int actualSize = 1;
            int expectedSize = test.size();
            ;
            if (actualSize != expectedSize) return false;

        }

        //(3) Verifying the true case of contains() method
        {
            boolean expected = true;
            boolean actual = test.contains(tweet1);
            if (actual != expected) return false;

        }

        //(4) Verifying the false case of contains() method
        {
            boolean expected = false;
            boolean actual = test.contains(tweet2);
            if (actual != expected) return false;

        }

        //(5) Verifying Head and Tail
        {
            Tweet expectedHead = tweet1;
            Tweet actualHead = test.getHead();
            if (actualHead != expectedHead) return false;

            Tweet expectedTail = tweet1;
            Tweet actualTail = test.getTail();
            if (actualTail != expectedTail) ;

        }

        //(6) Verfiying Head and Tail after adding another object to the end
        {
            test.addLast(tweet2);
            Tweet expectedHead = tweet1;
            Tweet actualHead = test.getHead();
            if (actualHead != expectedHead) return false;

            Tweet expectedTail = tweet2;
            Tweet actualTail = test.getTail();
            if (actualTail != expectedTail) return false;
        }

        //(7) Verifying head and Tail after adding to the beginning
        {
            test.addFirst(tweet3);
            Tweet expectedHead = tweet3;
            Tweet actualHead = test.getHead();
            if (actualHead != expectedHead) return false;

            Tweet expectedTail = tweet2;
            Tweet actualTail = test.getTail();
            if (actualTail != expectedTail) return false;

        }
        //(8) Verifying get() Method
        { // now we know that our list:  tweet3 --> tweet1 --> Tweet2
            Tweet expected0 = tweet3;
            Tweet actual0 = test.get(0);
            if (actual0 != expected0) return false;

            Tweet expected1 = tweet1;
            Tweet actual1 = test.get(1);
            if (actual1 != expected1) return false;

            Tweet expected2 = tweet2;
            Tweet actual2 = test.get(2);
            if (actual2 != expected2) return false;

        }
        return true;
    }

    public static boolean testInsertTweet() {
        Tweet.setCalendar(Calendar.getInstance());
        User user1 = new User("James");
        User user2 = new User("Kyle");
        Tweet tweet1 = new Tweet(user1, "Hi");
        Tweet tweet2 = new Tweet(user2, "How are you?");
        User user3 = new User("Francis");
        Tweet tweet3 = new Tweet(user3, "Computer Science");
        User user4 = new User("Bogota");
        Tweet tweet4 = new Tweet(user4, "Algorithms");

        TwitterFeed test1 = new TwitterFeed();

        //(1) adding to the beginning
        {
            test1.add(0, tweet1);
            int actualSize = test1.size();
            int expectedSize = 1;
            Tweet expectedGet = tweet1;
            Tweet actualGet = test1.get(0);
            Tweet actualHead = tweet1;
            Tweet expectedHead = test1.getHead();
            Tweet actualTail = tweet1;
            Tweet expectedTail = test1.getTail();

            if (actualSize != expectedSize || expectedGet != actualGet || actualHead != expectedHead
                    || actualTail != expectedTail) return false;
        }

        //(2) adding to the end
        {
            test1.add(1, tweet3);
            int actualSize = test1.size();
            int expectedSize = 2;
            Tweet expectedGet = tweet3;
            Tweet actualGet = test1.get(1);
            Tweet actualHead = tweet1;
            Tweet expectedHead = test1.getHead();
            Tweet actualTail = tweet3;
            Tweet expectedTail = test1.getTail();

            if (actualSize != expectedSize || expectedGet != actualGet ||
                    actualHead != expectedHead || actualTail != expectedTail)
                return false;
        }

        //(3) adding in the middle
        {
            test1.add(1, tweet2);
            int actualSize = test1.size();
            int expectedSize = 3;
            Tweet expectedGet = tweet2;
            Tweet actualGet = test1.get(1);
            Tweet actualHead = tweet1;
            Tweet expectedHead = test1.getHead();
            Tweet actualTail = tweet3;
            Tweet expectedTail = test1.getTail();
            if (actualSize != expectedSize || expectedGet != actualGet ||
                    actualHead != expectedHead || actualTail != expectedTail)
                return false;
        }

        //(4) checking the contains method
        {
            boolean actual1 = test1.contains(tweet1);
            boolean expected1 = true;

            boolean actual2 = test1.contains(tweet4);
            boolean expected2 = false;

            if (actual1 != expected1 || actual2 != expected2) return false;
        }

        //(5) checking indexOf method - valid case
        {
            int expected = 0;
            int actual = test1.indexOf(tweet1);
            if (actual != expected) return false;
        }

        //(6) checking indexOf method - invalid case
        {
            int expected = -1;
            int actual = test1.indexOf(tweet4);
            if (actual != expected) return false;
        }

        //(7)  Exception Edge Cases - index is negative
        {
            try {
                test1.add(-3, tweet4);
                return false;
            } catch (IndexOutOfBoundsException e) {

            } catch (Exception e) {
                return false;
            }

        }

        //(8)  Exception Edge Cases - index is greater than the size
        {
            // currently the size is three
            try {
                test1.add(6, tweet4);
                return false;
            } catch (IndexOutOfBoundsException e) {

            } catch (Exception e) {
                return false;
            }

        }
        return true;
    }

    public static boolean testDeleteTweet() {
        Tweet.setCalendar(Calendar.getInstance());
        User user1 = new User("James");
        User user2 = new User("Kyle");
        Tweet tweet1 = new Tweet(user1, "Hi");
        Tweet tweet2 = new Tweet(user2, "How are you?");
        User user3 = new User("Francis");
        Tweet tweet3 = new Tweet(user3, "Computer Science");
        User user4 = new User("Bogota");
        Tweet tweet4 = new Tweet(user4, "Algorithms");
        User user5 = new User("Miguel");
        Tweet tweet5 = new Tweet(user5, "Data Structures");


        TwitterFeed test2 = new TwitterFeed();

        test2.addFirst(tweet1);
        test2.addLast(tweet2);
        test2.addLast(tweet3);
        test2.addLast(tweet4);
        test2.addLast(tweet5);

        //(1) Verifying getSize(), getHead(), getTail() after adding five elements
        {
            // TwitterFeed: tweet1 --> tweet2 --> tweet3 --> tweet4 --> tweet5
            int actualSize = test2.size();
            int expectedSize = 5;
            Tweet actualHead = tweet1;
            Tweet expectedHead = test2.getHead();
            Tweet actualTail = tweet5;
            Tweet expectedTail = test2.getTail();

            if (actualSize != expectedSize || actualHead != expectedHead
                    || actualTail != expectedTail) return false;

        }

        //(2)  Exception Edge Cases - index is negative
        {
            try {
                test2.delete(-3);
                return false;
            } catch (IndexOutOfBoundsException e) {

            } catch (Exception e) {
                return false;
            }

        }

        //(3)  Exception Edge Cases - index is greater than the size
        {
            // currently the size is three
            try {
                test2.delete(6);
                return false;
            } catch (IndexOutOfBoundsException e) {

            } catch (Exception e) {
                return false;
            }

        }

        //(4) Verifying getSize(), getHead(), getTail, get() removing an element from middle
        {
            test2.delete(2);
            // TwitterFeed: tweet1 --> tweet2 --> tweet4 --> tweet5
            int actualSize = test2.size();
            int expectedSize = 4;
            Tweet expectedHead = tweet1;
            Tweet actualHead = test2.getHead();
            Tweet expectedTail = tweet5;
            Tweet actualTail = test2.getTail();
            Tweet actualGet = test2.get(2);
            Tweet expectedGet = tweet4;
            if (actualSize != expectedSize || actualHead != expectedHead || actualGet != expectedGet
                    || actualTail != expectedTail) return false;

        }

        //(5) Verifying getSize(), getHead(), getTail, get() removing an element from beginning
        {
            test2.delete(0);
            // TwitterFeed: tweet2 --> tweet4 --> tweet5
            int actualSize = test2.size();
            int expectedSize = 3;
            Tweet expectedHead = tweet2;
            Tweet actualHead = test2.getHead();
            Tweet expectedTail = tweet5;
            Tweet actualTail = test2.getTail();
            Tweet actualGet = test2.get(2);
            Tweet expectedGet = tweet5;
            if (actualSize != expectedSize || actualHead != expectedHead || actualGet != expectedGet
                    || actualTail != expectedTail) return false;

        }

        //(6) Verifying getSize(), getHead(), getTail, get() removing an element from ending
        {
            test2.delete(2);
            // TwitterFeed: tweet2 --> tweet4
            int actualSize = test2.size();
            int expectedSize = 2;
            Tweet expectedHead = tweet2;
            Tweet actualHead = test2.getHead();
            Tweet expectedTail = tweet4;
            Tweet actualTail = test2.getTail();
            Tweet actualGet = test2.get(1);
            Tweet expectedGet = tweet4;
            if (actualSize != expectedSize || actualHead != expectedHead || actualGet != expectedGet
                    || actualTail != expectedTail) return false;

        }
        test2.delete(1);

        //(7) Verifying getSize(), getHead(), getTail, get() removing the only element
        {
            test2.delete(0);
            // TwitterFeed: tweet2
            int actualSize = test2.size();
            int expectedSize = 0;
            if (actualSize != expectedSize) return false;

            try {
                Tweet actualHead = test2.getHead();
                return false;
            } catch (NoSuchElementException e) {

            } catch (Exception e) {
                return false;
            }

            try {
                Tweet actualTail = test2.getTail();
                return false;
            } catch (NoSuchElementException e) {

            } catch (Exception e) {
                return false;
            }

            try {
                Tweet actual = test2.get(2);
                return false;
            } catch (IndexOutOfBoundsException e) {

            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    public static boolean testChronoTwiterator() {
        Tweet.setCalendar(Calendar.getInstance());
        User user1 = new User("James");
        User user2 = new User("Kyle");
        Tweet tweet1 = new Tweet(user1, "Hi");
        Tweet tweet2 = new Tweet(user2, "How are you?");
        User user3 = new User("Francis");
        Tweet tweet3 = new Tweet(user3, "Computer Science");

        TwitterFeed test3 = new TwitterFeed();

        test3.addFirst(tweet1);
        test3.addLast(tweet2);
        test3.addLast(tweet3);

        for (Tweet actual : test3) {
            Tweet expected = tweet1;
            int iteration = 0;
            if (iteration == 0) {
                if (actual != expected) return false;
                iteration++;
            } else if (iteration == 1) {
                expected = tweet2;
                if (actual != expected) return false;
                iteration++;
            } else if (iteration == 2) {
                expected = tweet3;
                if (actual != expected) return false;
            }
        }

        return true;

    }

    public static boolean testVerifiedTwiterator() {

        Tweet.setCalendar(Calendar.getInstance());
        User user1 = new User("James");
        User user2 = new User("Kyle");
        Tweet tweet1 = new Tweet(user1, "Hi");
        Tweet tweet2 = new Tweet(user2, "How are you?");
        User user3 = new User("Williams");
        Tweet tweet3 = new Tweet(user3, "Computer Science");
        User user4 = new User("Bogota");
        Tweet tweet4 = new Tweet(user4, "Algorithms");
        User user5 = new User("Miguel");
        Tweet tweet5 = new Tweet(user5, "Data Structures");


        TwitterFeed test4 = new TwitterFeed();

        test4.addFirst(tweet1);
        test4.addLast(tweet2);
        test4.addLast(tweet3);
        test4.addLast(tweet4);
        test4.addLast(tweet5);

        // verify user 2 and 2
        user2.verify();
        user3.verify();

        for (Tweet actual : test4) {
            Tweet expected = tweet2;
            int iteration = 0;
            if (iteration == 0) {
                if (actual != expected) return false;
                iteration++;
            } else if (iteration == 1) {
                expected = tweet3;
                if (actual != expected) return false;
                iteration++;
            } else if (iteration == 2) {
                expected = null;
                if (actual != expected) return false;
            }
        }

        return true;
    }

    public static boolean testRatioTwiterator() {
        Tweet.setCalendar(Calendar.getInstance());
        User user1 = new User("James");
        User user2 = new User("Kyle");
        Tweet tweet1 = new Tweet(user1, "Hi");
        Tweet tweet2 = new Tweet(user2, "How are you?");
        User user3 = new User("Francis");
        Tweet tweet3 = new Tweet(user3, "Computer Science");
        User user4 = new User("Bogota");
        Tweet tweet4 = new Tweet(user4, "Algorithms");
        User user5 = new User("Miguel");
        Tweet tweet5 = new Tweet(user5, "Data Structures");


        TwitterFeed test5 = new TwitterFeed();

        test5.addFirst(tweet1);
        test5.addLast(tweet2);
        test5.addLast(tweet3);
        test5.addLast(tweet4);
        test5.addLast(tweet5);

        // 10 Likes of tweet2 and 5 retweets; 5 likes for tweet3; 5 retweet for tweet4
        for (int i = 0; i < 5; i++) {
            tweet2.like();
            tweet2.like();
            tweet2.retweet();
            tweet3.like();
            tweet4.retweet();
        }

        tweet3.retweet();
        tweet4.like();

        // at this point
        // tweet 1: 0 likes, 0 retweets, like ratio is undefined
        // tweet 2: 10 likes, 5 retweets, like ratio is 0.5 ≥ 0.5
        // tweet 3: 5 likes, 1 retweets, like ratio is 0.85 ≥ 0.5
        // tweet 4: 1 likes, 5 retweets, like ratio is undefined 0.12 ≤ 0.5
        // tweet 5: 0 likes, 0 retweets, like ratio is undefined

        for (Tweet actual : test5) {
            Tweet expected = tweet2;
            int iteration = 0;
            if (iteration == 0) {
                if (actual != expected) return false;
                iteration++;
            } else if (iteration == 1) {
                expected = tweet3;
                if (actual != expected) return false;
                iteration++;
            } else if (iteration == 2) {
                expected = null;
                if (actual != expected) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("testTweet: " + (testTweet() ? "pass" : "X"));
        System.out.println("testUser: " + (testUser() ? "pass" : "X"));
        System.out.println("testNode: " + (testNode() ? "pass" : "X"));
        System.out.println("testAddTweet: " + (testAddTweet() ? "pass" : "X"));
        System.out.println("testInsertTweet: " + (testInsertTweet() ? "pass" : "X"));
        System.out.println("testDeleteTweet: " + (testDeleteTweet() ? "pass" : "X"));
        System.out.println("testChronoTwiterator: " + (testChronoTwiterator() ? "pass" : "X"));
        System.out.println("testVerifiedTwiterator: " + (testVerifiedTwiterator() ? "pass" : "X"));
        System.out.println("testRatioTwiterator: " + (testRatioTwiterator() ? "pass" : "X"));
    }
}
