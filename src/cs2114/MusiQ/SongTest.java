package cs2114.MusiQ;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * A test class to test the Song class for functionality.
 *
 * @author Szu-Kai (Andy) Hsu (skhsu91)
 * @author Brian Skarda (bskarda)
 * @author Hang Lin (lin1412)
 * @version 2011.12.04
 */

public class SongTest
    extends TestCase
{

    /** The song to test. */
    private Song tester;


    /**
     * Sets the Songs to test up.
     */
    public void setUp()
    {
        tester = new Song(10, "bob", "Song 1", "Album", 5);
    }


    /**
     * Test get id.
     */
    public void testGetId()
    {
        assertEquals(10, (int)tester.getId());
    }


    /**
     * Test get artist.
     */
    public void testGetArtist()
    {
        assertEquals("bob", tester.getArtist());
    }


    /**
     * Test get title.
     */
    public void testGetTitle()
    {
        assertEquals("Song 1", tester.getTitle());
    }


    /**
     * Test get album.
     */
    public void testGetAlbum()
    {
        assertEquals("Album", tester.getAlbum());
    }


    /**
     * Test get duration.
     */
    public void testGetDuration()
    {
        assertEquals(5, (int)tester.getDuration());
    }
}
