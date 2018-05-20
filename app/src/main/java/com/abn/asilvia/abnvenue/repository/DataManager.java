package com.abn.asilvia.abnvenue.repository;

import android.arch.lifecycle.LiveData;

import com.abn.asilvia.abnvenue.api.ApiResponse;
import com.abn.asilvia.abnvenue.vo.details.VenueDetails;
import com.abn.asilvia.abnvenue.vo.search.Venues;

/**
 * Created by asilvia on 18/05/2018.
 */

public interface DataManager {
    LiveData<ApiResponse<Venues>> getVenues(String location);
    LiveData<ApiResponse<VenueDetails>> getVenueDetail(String venue_id);


}
