package com.phunware.homework.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.LinearLayout;

import com.phunware.homework.constants.Const;
import com.phunware.homework.fragments.FragmentDetail;
import com.phunware.homework.fragments.FragmentParent;
import com.phunware.homework.interfaces.VenueListener;
import com.phunware.homework.models.Venue;

import studio.aplaudo.com.hn.applaudostudios.R;

/**
 * Created by mac on 22/03/15.
 * Main Activity for app.
 */
public class MainActivity extends ActionBarActivity implements VenueListener {

    private static final String FRAGMENT_PARENT = "fragmentParent";
    private static final String FRAGMENT_DETAIL = "fragmentDetail";
    private Boolean mTwoPanel;

    /**
     * Activity used to control the UI presentation for tablets or small devices.
     */
    public MainActivity() {

    }

    /**
     * @param savedInstanceState which is used to save state information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dynamic);

        mTwoPanel = getResources().getBoolean(R.bool.isTablet);

        LinearLayout.LayoutParams layoutParamsPartial = new LinearLayout.LayoutParams(
                400, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams layoutParamsComplete = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);


        LinearLayout fragContainer = (LinearLayout) findViewById(R.id.llFragmentContainer);
        LinearLayout linearLayoutParent = new LinearLayout(this);
        linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        linearLayoutParent.setId(R.id.layoutParent);

        if (mTwoPanel) {
            linearLayoutParent.setLayoutParams(layoutParamsPartial);
        } else {
            linearLayoutParent.setLayoutParams(layoutParamsComplete);
        }

        getFragmentManager().beginTransaction().add(linearLayoutParent.getId(), new FragmentParent(), FRAGMENT_PARENT).commit();
        fragContainer.addView(linearLayoutParent);

        if (mTwoPanel) {
            LinearLayout linearLayoutDetail = new LinearLayout(this);
            linearLayoutDetail.setOrientation(LinearLayout.VERTICAL);
            linearLayoutDetail.setId(R.id.layoutDetail);
            linearLayoutDetail.setLayoutParams(layoutParamsComplete);

            getFragmentManager().beginTransaction().add(linearLayoutDetail.getId(), new FragmentDetail(), FRAGMENT_DETAIL).commit();
            fragContainer.addView(linearLayoutDetail);
        }

    }


    /**
     * @param venue which is used to send venue object between fragments on Activities.
     */
    @Override
    public void sendVenue(final Venue venue) {
        if (mTwoPanel) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            FragmentDetail fragmentDetail = (FragmentDetail) fragmentManager.findFragmentByTag(FRAGMENT_DETAIL);
            fragmentDetail.changeData(venue);
        } else {
            try {
                Intent intentCaller = new Intent(MainActivity.this, DetailActivity.class);
                intentCaller.putExtra(Const.VENUE_TRANSFER_DTO, venue);
                startActivity(intentCaller);
            } catch (Exception e) {
                Log.i(Const.ACTIVITY_ERROR_START, "");
            }

        }
    }

}
