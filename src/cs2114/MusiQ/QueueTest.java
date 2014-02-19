package cs2114.MusiQ;

import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  A class that tests the functionality of the Queue class.
 *
 * @author Szu-Kai (Andy) Hsu (skhsu91)
 * @author Brian Skarda (bskarda)
 * @author Hang Lin (lin1412)
 * @version 2011.12.04
 */

public class QueueTest extends TestCase
{

    /** The tested queue. */
    private Queue<String> test;
    /** The tested queue of songs. */
    private Queue<Song> songQueue;


    // ----------------------------------------------------------
    /**
     * Set up the queue for testing for all test methods below it.
     */
    public void setUp()
    {
        test = new Queue<String>();
        songQueue = new Queue<Song>();

        Song one = new Song(-1, "bob", "Song 1", "Album", 5);
        Song two = new Song(-1, "jim", "Song 2", "Album", 5);
        songQueue.enqueue(one);
        songQueue.enqueue(two);
    }


    // ----------------------------------------------------------
    /**
     * Tests the enqueue method to make sure that it properly inserts an
     * item into the queue.
     */
    public void testEnqueue()
    {
        // Test enqueue an empty queue
        test.enqueue("1");
        assertEquals(1, test.size());
        assertEquals("1", test.peek());

        // Test enqueue a non empty queue
        test.enqueue("2");
        assertEquals(2, test.size());
        assertEquals("1", test.peek());
    }


    // ----------------------------------------------------------
    /**
     * Tests the dequeue method to make sure it properly removes an item from
     * the queue when called upon.
     */
    public void testDequeue()
    {
        // Test the empty queue.
        assertNull(test.dequeue());

        // Test a queue with items.
        test.enqueue("1");
        assertEquals("1", test.dequeue());
    }


    // ----------------------------------------------------------
    /**
     * Tests peek to make sure it displays the top of the queue without
     * actually modifying the queue itself.
     */
    public void testPeek()
    {
        test.enqueue("1");
        assertEquals("1", test.peek());
    }


    // ----------------------------------------------------------
    /**
     * Test size to make sure it is returning the correct size of the queue
     * currently.
     */
    public void testSize()
    {
        test.enqueue("1");
        assertEquals(1, test.size());
        test.enqueue("2");
        assertEquals(2, test.size());
        test.enqueue("3");
        assertEquals(3, test.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests toString to make sure the overridden toString displays the queue
     * contents properly.
     */
    public void testToString()
    {
        System.out.println(songQueue.toString());
        System.out.println("Song 1 - bob\nSong 2 - jim\n");
        assertEquals("Song 1 - bob\nSong 2 - jim\n", songQueue.toString());
    }
}
