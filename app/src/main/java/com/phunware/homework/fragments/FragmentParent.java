package com.phunware.homework.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phunware.homework.AplaudoApp;
import com.phunware.homework.adapters.AdapterAmazonList;
import com.phunware.homework.constants.Const;
import com.phunware.homework.interfaces.VenueListener;
import com.phunware.homework.models.Venue;
import com.phunware.homework.models.VenueList;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

import studio.aplaudo.com.hn.applaudostudios.R;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Fragment used to show Main Parent Information.
 */
public class FragmentParent extends Fragment {

    private static final String TAG_JSON_ARRAY = "jarray_req";
    private String mJsonResponse = "";
    private ListView mLsvAmazonList;
    private ProgressDialog mAmazonProgressDialog;
    private VenueListener mVenueListener;

    /**
     *
     */
    public FragmentParent() {

    }

    /**
     * @param inflater           which is the inflater fragment.
     * @param container          which is the container.
     * @param savedInstanceState which is the Bundle save instance.
     * @return View object.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parent, container, false);
    }

    /**
     * @param savedInstanceState which is the Bundle save instance.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * @param savedInstanceState which is the Bundle save instance.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAmazonProgressDialog = new ProgressDialog(getActivity());
        mVenueListener = (VenueListener) getActivity();
        mLsvAmazonList = (ListView) getActivity().findViewById(R.id.lsvAmazonList);

        mLsvAmazonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Venue mVenue = VenueList.getsVenues().get(position);
                    mVenueListener.sendVenue(mVenue);
                } catch (Exception e) {
                    Log.e(Const.ACTIVITY_ERROR_ON_CLICK, Const.ACTIVITY_ERROR_ON_CLICK, e);
                }
            }
        });


        if (VenueList.getsVenues() == null || VenueList.getsVenues().isEmpty()) {
            if (mAmazonProgressDialog != null) {
                hideDialogProgress();
                showDialogProgress();
            }
            setDataList();
        } else {
            List<Venue> venues = VenueList.getsVenues();
            AdapterAmazonList amazonAdaptador = new AdapterAmazonList(getActivity(), venues);
            mLsvAmazonList.setAdapter(amazonAdaptador);
        }


    }

    /**
     * Method used to get Json Data by Http request REST service for Venue List information.
     */
    private void setDataList() {

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY_AMAZON,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mJsonResponse = response.toString();
                        try {
                            final GsonBuilder gsonBuilder = new GsonBuilder();
                            gsonBuilder.setDateFormat(Const.FORMAT_DATE_TIME);
                            final Gson gson = gsonBuilder.create();
                            final List<Venue> venues = Arrays.asList(gson.fromJson(mJsonResponse, Venue[].class));
                            VenueList.setsVenues(venues);
                            final AdapterAmazonList amazonAdaptador = new AdapterAmazonList(getActivity(), venues);
                            mLsvAmazonList.setAdapter(amazonAdaptador);
                            hideDialogProgress();
                        } catch (Exception ex) {
                            Log.e(Const.ACTIVITY_ERROR_LOADING_DATA, Const.ACTIVITY_ERROR_LOADING_DATA, ex);
                            hideDialogProgress();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialogProgress();
            }
        });

        AplaudoApp.getInstance().addToRequestQueue(req, TAG_JSON_ARRAY);

    }

    /**
     * Show Progress dialog.
     */
    private void showDialogProgress() {

        mAmazonProgressDialog.setMessage(getResources().getString(R.string.message_loading_data));
        mAmazonProgressDialog.setCancelable(false);
        mAmazonProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mAmazonProgressDialog.show();
    }


    /**
     * Hide Progress dialog.
     */
    private void hideDialogProgress() {

        mAmazonProgressDialog.hide();
        mAmazonProgressDialog.dismiss();

    }

    /**
     * Hide Progress dialog on Pause.
     */
    @Override
    public void onPause() {

        super.onPause();
        if (mAmazonProgressDialog != null) {
            hideDialogProgress();

        }

    }


}
