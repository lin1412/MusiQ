package cs2114.MusiQ;

import android.widget.TextView;
import android.content.Intent;
import android.media.MediaPlayer.OnCompletionListener;
import java.util.ArrayList;
import android.view.View;
import java.io.IOException;
import android.net.Uri;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.app.Activity;
import android.os.Bundle;

// -------------------------------------------------------------------------
/**
 * The MusiQ Acticity class that handles most of the controllers and functions
 * of the MusiQ program. Will have multiple methods that will handle the program
 * when it has music in the queue, when the current song finishes playing, and
 * any other possible situations that may arise when dealing with this music
 * player.
 *
 * @author Szu-Kai (Andy) Hsu (skhsu91)
 * @author Brian Skarda (bskarda)
 * @author Hang Lin (lin1412)
 * @version 2011.12.04
 */
public class MusiQActivity
    extends Activity
{
    private MediaPlayer       player;
    private MusicRetriever    ret;
    private Queue<Song>       queue;
    private ArrayList<Song>   list;
    private ArrayList<String> titleList;
    private TextView          queueView;
    private TextView          nowPlaying;

    /**
     * The request code for SecondActivity. This is passed to onActivityResult
     * to distinguish it from any other activities that you might start here.
     */
    private static final int  SONG_LIST_ACTIVITY = 1;


    // ----------------------------------------------------------
    /**
     * The onCreate method will instantiate all of the functions within it upon
     * startup of the program. Will make sure that the queue, the music player,
     * and parts of the view are all functioning before running.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        queue = new Queue<Song>();

        // Set views
        queueView = (TextView)findViewById(R.id.queue);
        nowPlaying = (TextView)findViewById(R.id.nowPlaying);

        // Music Retriever
        ret = new MusicRetriever(getContentResolver());
        ret.prepare();
        list = ret.getList();

        player = new MediaPlayer();

        // prints the number of songs for a test
        System.out.println(list.size());
        // playSong(list.get(0).getURI());

        titleList = new ArrayList<String>();

        for (Song cur : list)
        {
            if (cur != null)
            {
                System.out.println(cur.getTitle());
                titleList.add(cur.getTitle());
            }
            else
            {
                System.out.println("null cur");
            }
        }

    }


    // ----------------------------------------------------------
    /**
     * Method used in order to call the song and start streaming it at the
     * proper quality on the android device.
     *
     * @param uri
     *            the uri of the song that is being requested to be played.
     */
    private void playSong(Uri uri)
    {
        player.reset();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try
        {
            player.setDataSource(getApplicationContext(), uri);
            player.prepare();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (IllegalStateException e)
        {

            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        player.start();

        // Setup listener so next song starts automatically
        player.setOnCompletionListener(new OnCompletionListener()
        {

            /**
             * Create an onCompletion method that will commit a certain act
             * when the current song finishes playing.
             *
             * @param   mp  the music player that is currently being viewed.
             */
            public void onCompletion(MediaPlayer mp)
            {
                if (queue.size() != 0)
                {
                    // skip to next song
                    skipSong();
                }
            }
        });
    }


    // ----------------------------------------------------------
    /**
     * On add button click.
     *
     * @param view  the view that is being dealt with currently.
     */
    public void onAddButtonClick(View view)
    {
        // open up list of songs
        Intent intent = new Intent(this, SongListActivity.class);
        intent.putExtra("List of all the songs", titleList);
        startActivityForResult(intent, SONG_LIST_ACTIVITY);
    }


    // ----------------------------------------------------------
    /**
     * Method that will take care of the music player when the skip button is
     * clicked.
     *
     * @param view the view being looked on at the moment.
     */
    public void onSkipButtonClick(View view)
    {
        skipSong();
    }


    // ----------------------------------------------------------
    /**
     * Method that will make sure that the play/pause button actually plays and
     * pauses the song when clicked.
     *
     * @param view  the view that is being looked upon at the moment.
     */
    public void onPlayPauseButtonClick(View view)
    {
        if (queue.size() != 0)
        {
            if (player.isPlaying())
            {
                player.pause();
            }
            else
            {
                player.start();
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * gives how many songs are still in the queue.
     * @return the number of songs in the queue.
     */
    public int queueSize()
    {
        return queue.size();
    }

    // ----------------------------------------------------------
    /**
     * gives the number of playable music on the phone.
     * @return the possible number of music that could be played.
     */
    public int retrieverSize()
    {
        return ret.getList().size();
    }

    // ----------------------------------------------------------
    /**
     * To see is the music is currently playing.
     * @return whether the music is playing.
     */
    public boolean isPlaying()
    {
        return player.isPlaying();
    }


    // ----------------------------------------------------------
    /**
     * Called when an activity that we started returns a result back to us.
     *
     * @param requestCode
     *            the request code, an integer that identifies which activity
     *            returned
     * @param resultCode
     *            either RESULT_OK or RESULT_CANCELLED
     * @param data
     *            the data that the activity sent back in the form of an Intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SONG_LIST_ACTIVITY && resultCode == RESULT_OK)
        {
            // get intent data
            // System.out.println(data.getExtras().containsKey("Chosen song"));
            // System.out.println(data.getIntExtra("Chosen song", -1));
            queue.enqueue(list.get(data.getIntExtra("Chosen song", -1)));
        }

        if (queue.size() == 1)
        {
            playSong(queue.peek().getURI());
        }

        updateQueueView();

        super.onActivityResult(requestCode, resultCode, data);
    }


    // ----------------------------------------------------------
    /**
     * Called when the activity is destroyed. Will release the player and set
     * the player to null so that the media player is no longer taking up
     * processing power from the android device.
     */
    @Override
    public void onDestroy()
    {
        player.release();
        player = null;
        super.onDestroy();
    }


    /**
     * Update the queue view for both the queue of songs and the now playing
     * song, which so happens to be just the song on the top of the queue.
     */
    private void updateQueueView()
    {
        if (queue.size() != 0)
        {
            queueView.setText(queue.toString());
            nowPlaying.setText(queue.peek().getTitle() + " - "
            + queue.peek().getArtist());
        }
        else
        {
            queueView.setText("Please select songs to place into the queue.");
            nowPlaying.setText("");
        }

    }



    /**
     * Skip the current song. If the queue is empty this function will not
     * do anything.
     */
    private void skipSong()
    {
        if (queue.size() != 0)
        {
            // skip to next song
            if(queue.size() == 1)
            {
                queue.dequeue();
                player.stop();
            }
            else
            {
                queue.dequeue();
                playSong(queue.peek().getURI());
            }
        }
        updateQueueView();
    }

    /**
     *  return the list os songs in the queue.
     * @return the toString of the queue
     */
    public String queueString()
    {
        return queue.toString();
    }
}
