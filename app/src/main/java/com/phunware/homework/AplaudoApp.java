package com.phunware.homework;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mac on 17/03/2015.
 * Used to get request for Images.
 */
public class AplaudoApp extends Application {

    public static final String TAG = AplaudoApp.class.getSimpleName();
    private static AplaudoApp sInstance;
    private RequestQueue mRequestQueue;

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    /**
     * @return AplaudoApp instance.
     */
    public static synchronized AplaudoApp getInstance() {
        return sInstance;
    }


    /**
     * @return RequestQueue.
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    /**
     * @param req which is request.
     * @param tag which is tag.
     * @param <T> which is generic type.
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }


}