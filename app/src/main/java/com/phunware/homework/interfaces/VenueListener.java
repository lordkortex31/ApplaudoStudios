package com.phunware.homework.interfaces;

import com.phunware.homework.models.Venue;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Interface used to communicate the click parent fragment lis with detail fragment.
 */
public interface VenueListener {

    public void sendVenue(final Venue venue);

}
