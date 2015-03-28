package com.phunware.homework.fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phunware.homework.models.ScheduleItemParceable;
import com.phunware.homework.models.Venue;
import com.squareup.picasso.Picasso;

import studio.aplaudo.com.hn.applaudostudios.R;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Fragment used to show detail Information.
 */
public class FragmentDetail extends Fragment {


    private static final String SEPARATOR = " , ";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public  FragmentDetail(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void changeData(Venue venue) {

        ImageView imgAmazonList;
        TextView txvAmazonListDescription,txvAmazonListAddress,txvAmazonListState,txvAmazonListCalendar;

        imgAmazonList = (ImageView) getActivity().findViewById(R.id.imgAmazonDetailView);
        txvAmazonListDescription = (TextView) getActivity().findViewById(R.id.txvAmazonDetailDescription);
        txvAmazonListAddress = (TextView) getActivity().findViewById(R.id.txvAmazonDetailAddress);
        txvAmazonListState = (TextView) getActivity().findViewById(R.id.txvAmazonDetailState);
        txvAmazonListCalendar = (TextView) getActivity().findViewById(R.id.txvAmazonDetailCalendar);


        if (venue != null) {
            String mUrlImage = venue.getImageUrl();

            if (!mUrlImage.equals("")) {
                Picasso.with(getActivity()).load(mUrlImage).placeholder(R.mipmap.loadingimage).error(R.mipmap.noimage).into(imgAmazonList);
            } else {
                Resources res = getActivity().getResources();
                Drawable drawable = res.getDrawable(R.mipmap.noimage);
                imgAmazonList.setImageDrawable(drawable);
            }

            String strComplexDate = getComplexDate(venue);

            txvAmazonListDescription.setText(venue.getName().concat(SEPARATOR).concat(venue.getDescription()));
            txvAmazonListAddress.setText(venue.getAddress());
            txvAmazonListState.setText(venue.getCity().concat(SEPARATOR).concat(venue.getState()).concat(SEPARATOR).concat(venue.getZip()));
            txvAmazonListCalendar.setText(strComplexDate);
        }
    }


    private String buildDate(final String sStartDate, final String sEndDate) {

        StringBuilder strBuilderDate = new StringBuilder();
        strBuilderDate.append(sStartDate);
        strBuilderDate.append(LINE_SEPARATOR);
        strBuilderDate.append(sEndDate);
        strBuilderDate.append(LINE_SEPARATOR);

        return strBuilderDate.toString();

    }


    private String getComplexDate(Venue mVenue) {
        StringBuilder strComplexDate = new StringBuilder();
        for (ScheduleItemParceable mSchedule : mVenue.getSchedule()) {
            if (mSchedule != null) {
                String sStartDate = mSchedule.getStartDate();
                String sEndDate = mSchedule.getEndDate();
                String sComplexDate = buildDate(sStartDate, sEndDate);
                strComplexDate.append(sComplexDate);
            }
        }
        return strComplexDate.toString();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id== android.R.id.home){
            getActivity().finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
