package studio.aplaudo.com.hn.applaudostudios;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import studio.aplaudo.com.hn.adapters.Adapter_amazon_list;
import studio.aplaudo.com.hn.api.AppController;
import studio.aplaudo.com.hn.constants.Const;
import studio.aplaudo.com.hn.interfaces.Communicator;
import studio.aplaudo.com.hn.model.Venue;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Fragment used to show Main Parent Information.
 */
public class FragmentParent extends Fragment implements AdapterView.OnItemClickListener {

    private static final String MTAG_JSON_ARRAY = "jarray_req";
    private String mJsonRespuesta = "";
    private ListView mlsvAmazonList;
    private List<Venue> mVenues = new ArrayList<>();
    private ProgressDialog mAmazonProgressdialog;
    private Communicator communicator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parent, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        communicator = (Communicator) getActivity();

        mAmazonProgressdialog = new ProgressDialog(getActivity());
        mAmazonProgressdialog.setMessage("Cargando Lista de Amazon Items");
        mAmazonProgressdialog.setCancelable(false);
        mAmazonProgressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mAmazonProgressdialog.show();


        mlsvAmazonList = (ListView) getActivity().findViewById(R.id.lsvAmazonList);

        mlsvAmazonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Venue mVenue = mVenues.get(position);
                    communicator.sendVenue(mVenue);
                } catch (Exception e) {
                    Log.e("Error On Click", "Error On Click", e);
                }
            }
        });

        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY_AMAZON,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mJsonRespuesta = response.toString();
                        try {
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                            Gson gson = gsonBuilder.create();
                            mVenues = Arrays.asList(gson.fromJson(mJsonRespuesta, Venue[].class));
                            Adapter_amazon_list mAdaptador = new Adapter_amazon_list(getActivity(), mVenues);
                            mlsvAmazonList.setAdapter(mAdaptador);
                            mAmazonProgressdialog.hide();
                        } catch (Exception ex) {
                            Log.e("Error Loading Fragment", "Error Loading Data", ex);
                            mAmazonProgressdialog.hide();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mAmazonProgressdialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(req, MTAG_JSON_ARRAY);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
