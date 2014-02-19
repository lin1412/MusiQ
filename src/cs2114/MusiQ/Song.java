package cs2114.MusiQ;

import android.content.ContentUris;
import android.net.Uri;

// -------------------------------------------------------------------------
/**
 *  Individual song items for the music player.
 *
 * @author Szu-Kai (Andy) Hsu (skhsu91)
 * @author Brian Skarda (bskarda)
 * @author Hang Lin (lin1412)
 * @version 2011.12.04
 */

/**
 * The Class Item.
 */
public class Song {

    /** The id. */
    private long        id;

    /** The artist. */
    private String      artist;

    /** The title. */
    private String      title;

    /** The album. */
    private String      album;

    /** The duration. */
    private long        duration;

    /**
     * Instantiates a new item.
     *
     * @param id the id
     * @param artist the artist
     * @param title the title
     * @param album the album
     * @param duration the duration
     */
    public Song(long id, String artist, String title, String album,
        long duration) {

        this.id = id;
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.duration = duration;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the artist.
     *
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the album.
     *
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Gets the duration.
     *
     * @return the duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Gets the uRI.
     *
     * @return the uRI
     */
    public Uri getURI() {
        return ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                id);
    }
}

