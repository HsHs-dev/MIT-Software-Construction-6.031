/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     * 
     * Testing strategy:
     * Partition for getTimespan(tweets) -> Timespan
     * tweets.size: 1, > 1
     * 
     * Partion for getMentionedUsers(tweets) -> Set<String>
     * Set<String>.size() = 0
     * Set<String>.size() = 1
     * Set<String>.size() > 1
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "hassan", "this person @bbitdiddle has a weird name", d1);
    private static final Tweet tweet4 = new Tweet(4, "mahmoudosan", "yo, who are @hassan and @alyssa???", d1);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // test Extract.getTimeSpan(tweets) tweets.size() > 1
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }

    // test Extract.getTimeSpan(tweets) tweets.size() = 1
    @Test
    public void testGetTimeSpanOneTweet() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1));
        assertEquals("Expected start", d1, timespan.getStart());
        assertEquals("Expected end", d1, timespan.getEnd());
    }
   
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    @Test
    public void testGetMentionedUsersOneUser() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));

        assertEquals("Expected set: ", Set.of("bbitdiddle"), mentionedUsers);

    }

    @Test
    public void testGetMentionedUsersTwoUsers() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3, tweet4));

        assertEquals("Expected set: ", Set.of("bbitdiddle", "hassan", "alyssa"), mentionedUsers);
    }


    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
