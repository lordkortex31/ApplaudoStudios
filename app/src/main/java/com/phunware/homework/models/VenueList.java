package com.phunware.homework.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CortesMoncada on 18/03/2015.
 * Class used to store the venue list object.
 */
public final class VenueList {



    private static List<Venue> sVenues = new ArrayList<>();

    private VenueList(){


    }

    public static List<Venue> getsVenues() {
        return sVenues;
    }

    public static void setsVenues(List<Venue> sVenues) {
        VenueList.sVenues = sVenues;
    }
}
