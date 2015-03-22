package studio.aplaudo.com.hn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.LinearLayout;

import studio.aplaudo.com.hn.applaudostudios.R;
import studio.aplaudo.com.hn.fragments.FragmentDetail;
import studio.aplaudo.com.hn.fragments.FragmentParent;
import studio.aplaudo.com.hn.interfaces.VenueListener;
import studio.aplaudo.com.hn.models.Venue;

/**
 * Created by mac on 22/03/15.
 * Main Activity for app.
 */
public class MainActivity extends ActionBarActivity implements VenueListener {

    private static final String FRAGMENT_PARENT ="fragmentParent";
    private static final String FRAGMENT_DETAIL ="fragmentDetail";

    private Boolean twoPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dynamic);

        twoPanel = getResources().getBoolean(R.bool.isTablet);

        LinearLayout.LayoutParams layoutParamsPartial = new LinearLayout.LayoutParams(
                400, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams layoutParamsComplete = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);


        LinearLayout fragContainer = (LinearLayout) findViewById(R.id.llFragmentContainer);
        LinearLayout linearLayoutParent = new LinearLayout(this);
        linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        linearLayoutParent.setId(R.id.layoutParent);

        if (twoPanel){
            linearLayoutParent.setLayoutParams(layoutParamsPartial);
        }else{
            linearLayoutParent.setLayoutParams(layoutParamsComplete);
        }

        getFragmentManager().beginTransaction().add(linearLayoutParent.getId(), new FragmentParent(), FRAGMENT_PARENT).commit();
        fragContainer.addView(linearLayoutParent);

        if (twoPanel){
            LinearLayout linearLayoutDetail = new LinearLayout(this);
            linearLayoutDetail.setOrientation(LinearLayout.VERTICAL);
            linearLayoutDetail.setId(R.id.layoutDetail);
            linearLayoutDetail.setLayoutParams(layoutParamsComplete);

            getFragmentManager().beginTransaction().add(linearLayoutDetail.getId(), new FragmentDetail(), FRAGMENT_DETAIL).commit();
            fragContainer.addView(linearLayoutDetail);
        }

    }


    @Override
    public void sendVenue(Venue venue) {
        if (twoPanel) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            FragmentDetail fragmentDetail = (FragmentDetail) fragmentManager.findFragmentByTag(FRAGMENT_DETAIL);
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
                Log.i("Error On Start Activity","");
            }

        }
    }

}
