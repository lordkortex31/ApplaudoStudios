package com.phunware.homework.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.phunware.homework.constants.Const;
import com.phunware.homework.fragments.FragmentDetail;
import com.phunware.homework.models.Venue;

import studio.aplaudo.com.hn.applaudostudios.R;

/**
 * Created by mac on 17/03/2015.
 * Class used to Show detail information
 */
public class DetailActivity extends ActionBarActivity {

    /**
     * Activity used to show detail information about venues with an image Header.
     */
    public DetailActivity(){

    }

    /**
     *
     * @param savedInstanceState which is used to save state information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Venue venue =  getIntent().getParcelableExtra(Const.VENUE_TRANSFER_DTO);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentDetail fragmentDetail = (FragmentDetail) fragmentManager.findFragmentById(R.id.fragmentDetailNfl);
        fragmentDetail.changeData(venue);

    }

    /**
     *
     * @param menu which represents a resource menu option.
     * @return boolean value.
     */
 @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();

        if (id== android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
