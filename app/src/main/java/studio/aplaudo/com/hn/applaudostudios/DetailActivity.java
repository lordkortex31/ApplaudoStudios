package studio.aplaudo.com.hn.applaudostudios;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

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
}
