package com.abn.asilvia.abnvenue.repository;

import android.arch.lifecycle.LiveData;

import com.abn.asilvia.abnvenue.api.ApiResponse;
import com.abn.asilvia.abnvenue.db.LocalVenues;
import com.abn.asilvia.abnvenue.vo.details.VenueDetails;
import com.abn.asilvia.abnvenue.vo.search.Venues;

import java.util.List;

import retrofit2.Call;

/**
 * Created by asilvia on 18/05/2018.
 */

public interface DataManager {
    LiveData<ApiResponse<Venues>> getVenues(String location);
    Call<Venues> getVenuesCall(String location);
    Call<VenueDetails> getVenueDetail(String venue_id);
    List<LocalVenues> getSavedVenues();
    void deleteAllVenues();
    void saveVenues(List<LocalVenues> list);
    void saveVenue(LocalVenues localVenue);
    LiveData<LocalVenues> findVenue(String id);
    String getLastLocation();
    void setLastLocation(String location);
    void updateVenues();



}
