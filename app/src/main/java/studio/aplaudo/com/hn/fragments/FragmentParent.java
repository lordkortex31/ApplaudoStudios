package studio.aplaudo.com.hn.fragments;

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

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

import studio.aplaudo.com.hn.adapters.Adapter_amazon_list;
import studio.aplaudo.com.hn.apis.AplaudoApp;
import studio.aplaudo.com.hn.applaudostudios.R;
import studio.aplaudo.com.hn.constants.Const;
import studio.aplaudo.com.hn.interfaces.VenueListener;
import studio.aplaudo.com.hn.models.Venue;
import studio.aplaudo.com.hn.models.VenueList;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Fragment used to show Main Parent Information.
 */
public class FragmentParent extends Fragment   {

    private static final String MTAG_JSON_ARRAY = "jarray_req";
    private String mJsonRespuesta = "";
    private ListView mlsvAmazonList;
    private ProgressDialog mAmazonProgressdialog;
    private VenueListener communicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parent, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        communicator = (VenueListener) getActivity();
        mlsvAmazonList = (ListView) getActivity().findViewById(R.id.lsvAmazonList);

        mlsvAmazonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Venue mVenue = VenueList.getmVenues().get(position);
                    communicator.sendVenue(mVenue);
                } catch (Exception e) {
                    Log.e("Error On Click", "Error On Click", e);
                }
            }
        });

        setDataList();

    }

    private  void setDataList(){

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY_AMAZON,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mJsonRespuesta = response.toString();
                        try {
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                            Gson gson = gsonBuilder.create();
                            List<Venue> venues = Arrays.asList(gson.fromJson(mJsonRespuesta, Venue[].class));
                            VenueList.setmVenues(venues);
                            Adapter_amazon_list mAdaptador = new Adapter_amazon_list(getActivity(), venues);
                            mlsvAmazonList.setAdapter(mAdaptador);
                            mAmazonProgressdialog.hide();
                        } catch (Exception ex) {
                            Log.e("Error Loading Data", "Error Loading Data", ex);
                            mAmazonProgressdialog.hide();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mAmazonProgressdialog.hide();
            }
        });

        if (VenueList.getmVenues() == null || VenueList.getmVenues().isEmpty()) {
            showDialogProgress();
            AplaudoApp.getInstance().addToRequestQueue(req, MTAG_JSON_ARRAY);
        }else{
            List<Venue> venues = VenueList.getmVenues();
            Adapter_amazon_list mAdaptador = new Adapter_amazon_list(getActivity(), venues);
            mlsvAmazonList.setAdapter(mAdaptador);
        }


    }

    private void showDialogProgress(){

        mAmazonProgressdialog = new ProgressDialog(getActivity());
        mAmazonProgressdialog.setMessage("Cargando Lista de Amazon Items");
        mAmazonProgressdialog.setCancelable(false);
        mAmazonProgressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mAmazonProgressdialog.show();
    }



}
