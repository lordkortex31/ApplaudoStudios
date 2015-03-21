package studio.aplaudo.com.hn.interfaces;

import studio.aplaudo.com.hn.models.Venue;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Interface used to communicate the click parent fragment lis with detail fragment.
 */
public interface VenueListener {

    public void sendVenue(Venue venue);

}
