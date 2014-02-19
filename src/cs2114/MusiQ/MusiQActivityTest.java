package cs2114.MusiQ;

import android.widget.TextView;
import android.widget.Button;

// -------------------------------------------------------------------------
/**
 * A testing class for the MusiQ activity class.
 *
 * @author Szu-Kai (Andy) Hsu (skhsu91)
 * @author Brian Skarda (bskarda)
 * @author Hang Lin (lin1412)
 * @version 2011.12.04
 */
public class MusiQActivityTest
    extends student.AndroidTestCase<MusiQActivity>
{
    private Button   skip;
    private Button   add;
    private Button   playPause;
    private TextView nowPlaying;
    private TextView queue;
    //private ListView songList;


    // ----------------------------------------------------------
    /**
     * Create a new MusiQActivityTest object.
     */
    public MusiQActivityTest()
    {
        super(MusiQActivity.class);
    }


    // ----------------------------------------------------------
    /**
     * Set up the class for each test
     */
    public void setUp()
    {
        skip = getView(Button.class, R.id.skipButton);
        add = getView(Button.class, R.id.addButton);
        playPause = getView(Button.class, R.id.playPauseButton);
        nowPlaying = getView(TextView.class, R.id.nowPlaying);
        queue = getView(TextView.class, R.id.queue);
    }


    // ----------------------------------------------------------
    /**
     * Adds music into the Queue.
     */
    @SuppressWarnings("static-access")
    public void testAdd()
    {

        // test that there is no music in the queue.
        assertEquals( queue.getText(),
            "Please select songs to place into the queue.");
        click(add);
        try
        {
            Thread.currentThread().sleep(4000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //test the see if the song has been added.
        assertEquals( queue.getText(),"You and Your Heart - Jack Johnson\n");

        // add song by click on the coordinates.
        click(add);
        try
        {
            Thread.currentThread().sleep(4000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        click(add);
        try
        {
            Thread.currentThread().sleep(4000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //test if the number of songs increased
        assertEquals( getActivity().queueSize(), 3);

    }


    // ----------------------------------------------------------
    /**
     * Test the skipping function of the player.
     */
    public void testSkip()
    {
        testAdd();
        assertEquals( nowPlaying.getText(),"You and Your Heart - Jack Johnson");

        //click on the skip button
        click(skip);

        assertEquals( queue.getText(), getActivity().queueString());
        assertEquals( nowPlaying.getText(),"To the Sea - Jack Johnson");

        //test if the number of songs decreased.
        assertEquals( getActivity().queueSize(), 2);

        //click on the skip button
        click(skip);

        assertEquals( queue.getText(), getActivity().queueString());
        assertEquals( nowPlaying.getText(),"No Good with Faces - Jack Johnson");

        //test if the number of songs decreased.
        assertEquals( getActivity().queueSize(), 1);
        assertTrue(getActivity().isPlaying());
    }


    // ----------------------------------------------------------
    /**
     * Test the Play and Pause function of the player.
     */
    public void testPlayPause()
    {
        testAdd();

        //make sure the song is currently playing
        assertTrue(getActivity().isPlaying());

        //make sure the song is paused.
        click(playPause);
        assertFalse(getActivity().isPlaying());

        //make sure the song is currently playing
        click(playPause);
        assertTrue(getActivity().isPlaying());

    }

    // ----------------------------------------------------------
    /**
     * test the getList from the MusicRetriever.
     */
    public void testMusicRetriever()
    {
        testAdd();
        assertEquals( getActivity().retrieverSize(), 3);
    }

}
