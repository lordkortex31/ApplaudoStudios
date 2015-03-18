package studio.aplaudo.com.hn.applaudostudios;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import studio.aplaudo.com.hn.interfaces.Communicator;
import studio.aplaudo.com.hn.models.Venue;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Main Activity is launcher Activity to show Nfl List.
 */
public class MainActivity extends ActionBarActivity implements Communicator {

    private Boolean twoPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragmentDetailNfl) != null) {
            twoPanel = Boolean.TRUE;
        } else {
            twoPanel = Boolean.FALSE;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return Boolean.TRUE;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sendVenue(Venue venue) {

        if (twoPanel) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentDetail fragmentDetail = (FragmentDetail) fragmentManager.findFragmentById(R.id.fragmentDetailNfl);
            fragmentDetail.changeData(venue);
        } else {
            Bundle bundleVenue = new Bundle();
            Intent intent = new Intent();
            intent.setClass(this, DetailActivity.class);
            intent.putExtras(bundleVenue);
            startActivity(intent);
            try {
                Intent ourintent = new Intent(MainActivity.this, DetailActivity.class);
                ourintent.putExtra("MyClassVenue", venue);
                startActivity(ourintent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
