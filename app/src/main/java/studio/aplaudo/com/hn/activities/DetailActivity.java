package studio.aplaudo.com.hn.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import studio.aplaudo.com.hn.applaudostudios.R;
import studio.aplaudo.com.hn.fragments.FragmentDetail;
import studio.aplaudo.com.hn.models.Venue;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Show detail information
 */
public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Venue mVenue = (Venue) getIntent().getSerializableExtra("MyClassVenue");

        FragmentManager fragmentManager = getFragmentManager();
        FragmentDetail fragmentDetail = (FragmentDetail) fragmentManager.findFragmentById(R.id.fragmentDetailNfl);
        fragmentDetail.changeData(mVenue);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id== android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
