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
 * Created by CortesMoncada on 17/03/2015.
 * Show detail information
 */
public class DetailActivity extends ActionBarActivity {

    public DetailActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Venue venue =  getIntent().getParcelableExtra(Const.VENUE_TRANSFER_DTO);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentDetail fragmentDetail = (FragmentDetail) fragmentManager.findFragmentById(R.id.fragmentDetailNfl);
        fragmentDetail.changeData(venue);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
