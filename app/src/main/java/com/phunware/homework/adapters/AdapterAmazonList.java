package com.phunware.homework.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phunware.homework.models.Venue;

import java.util.List;

import studio.aplaudo.com.hn.applaudostudios.R;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Adapter to fill amazon List on ListFragment.
 */
public class AdapterAmazonList extends BaseAdapter {

    private List<Venue> mData;
    private static LayoutInflater mInflater = null;


    public AdapterAmazonList(Activity activity, List<Venue> venueList) {
        mData = venueList;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null) {
            vi = mInflater.inflate(R.layout.activity_amazon_listview_item_main, null);
        }

        Venue venue = mData.get(position);

        TextView txvAddress = (TextView) vi.findViewById(R.id.txvAddress);
        TextView txvCityDescription = (TextView) vi.findViewById(R.id.txvCityDescription);

        txvAddress.setText(venue.getAddress());
        txvCityDescription.setText(venue.getName() + ',' + venue.getCity() + ',' + venue.getState());

        return vi;
    }


}
