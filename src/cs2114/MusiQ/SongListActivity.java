package cs2114.MusiQ;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import android.os.Bundle;
import android.app.ListActivity;

// -------------------------------------------------------------------------
/**
 * The activity to display the ListView of all the possible songs to add into
 * the music player queue.
 *
 * @author Szu-Kai (Andy) Hsu (skhsu91)
 * @author Brian Skarda (bskarda)
 * @author Hang Lin (lin1412)
 * @version 2011.12.04
 */

public class SongListActivity
    extends ListActivity
{
    private ArrayList<String> list;


    // ----------------------------------------------------------
    /**
     * Creates the Activity when the method is called.
     *
     * @param icicle
     *            the bundle passed in to the method
     */
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.songlist);

        if (getIntent().getExtras().size() != 0)
        {
            // System.out.println(getIntent().getExtras().keySet());
            // Object o = getIntent().getExtras().get("List of all the songs");
            list = getIntent().getStringArrayListExtra("List of all the songs");
            ArrayAdapter<String> songList =
                new ArrayAdapter<String>(this, R.layout.songitem, list);
            setListAdapter(songList);
        }

    }


    // ----------------------------------------------------------
    /**
     * React when the List item is clicked.
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent intent = new Intent();
        intent.putExtra("Chosen song", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
