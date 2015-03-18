package studio.aplaudo.com.hn.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CortesMoncada on 18/03/2015.
 */
public final class VenueList {



    private static List<Venue> mVenues = new ArrayList<>();

    private VenueList(){


    }

    public static List<Venue> getmVenues() {
        return mVenues;
    }

    public static void setmVenues(List<Venue> mVenues) {
        VenueList.mVenues = mVenues;
    }
}
