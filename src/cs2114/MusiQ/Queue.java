package cs2114.MusiQ;

import java.util.LinkedList;


// -------------------------------------------------------------------------
/**
 * The queue that will be used to store the songs that are currently in the
 * queue. Will have the basic functions found in the queue as well as a custom
 * toString that will display the list.
 *
 * @param <E>
 * @author Szu-Kai (Andy) Hsu (skhsu91)
 * @author Brian Skarda (bskarda)
 * @author Hang Lin (lin1412)
 * @version 2011.12.04
 */
public class Queue<E>
{
    private LinkedList<E> data;
    private int           size;


    // ----------------------------------------------------------
    /**
     * Create a new Queue object with the default size 0 and instantiating the
     * data object.
     */
    public Queue()
    {
        data = new LinkedList<E>();
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * Places a new object into the queue so long as the object itself is not
     * null.
     *
     * @param o
     *            the object being placed into the music queue.
     */
    @SuppressWarnings("unchecked")
    public void enqueue(Object o)
    {
        if (o != null)
        {
            data.add((E)o);
            size++;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets rid of the object at the front of the queue and returns it unless
     * the size of the queue is 0.
     *
     * @return the object that is at the front of the queue.
     */
    public E dequeue()
    {
        if (size != 0)
        {
            size--;
            return data.removeFirst();
        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * Returns the object at the front of the queue without actually modifying
     * the queue itself.
     *
     * @return the object currently at the front of the queue.
     */
    public E peek()
    {
        return data.getFirst();
    }


    // ----------------------------------------------------------
    /**
     * Returns the integer value of the current size of the queue.
     *
     * @return the current size of the queue.
     */
    public int size()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Gives a String representation of the entire queue by going through the
     * queue and adding it onto an existing string object. Will not run if
     * the queue's size is 0.
     *
     * @return the current size of the queue.
     */
    @Override
    public String toString()
    {
        String theQueue = "";

        if (size != 0)
        {
            for (int i = 0; i < size; i++)
            {
                theQueue +=
                    ((Song)data.get(i)).getTitle() + " - "
                        + ((Song)data.get(i)).getArtist() + "\n";
            }
        }

        return theQueue;
    }
}
