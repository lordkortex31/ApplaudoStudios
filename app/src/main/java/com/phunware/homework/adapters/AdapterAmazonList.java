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

    /**
     *
     * @param activity which represents context activity parent.
     * @param venueList is a {@link com.phunware.homework.models.Venue} object.
     */
    public AdapterAmazonList(final Activity activity, final List<Venue> venueList) {
        mData = venueList;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     *
     * @return the {@link com.phunware.homework.models.Venue} object size.
     */
    public int getCount() {
        return mData.size();
    }

    /**
     *
     * @param position which is the current position on element list.
     * @return object.
     */
    public Object getItem(final int position) {
        return position;
    }

    /**
     *
     * @param position which is the current position on element list.
     * @return long value.
     */
    public long getItemId(final int position) {
        return position;
    }

    /**
     *
     * @param position which is the current position on element list.
     * @param convertView which is the view.
     * @param parent which is the view parent.
     * @return view object.
     */
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
