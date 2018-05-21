package com.abn.asilvia.abnvenue.api;

import android.arch.lifecycle.LiveData;

import com.abn.asilvia.abnvenue.vo.details.VenueDetails;
import com.abn.asilvia.abnvenue.vo.search.Venues;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by asilvia on 18/05/2018.
 * Perform api calls
 */

public interface MainApiHelper {

    @GET("search")
    LiveData<ApiResponse<Venues>> getVenueByLocation(@Query("client_id") String client_id,
                                                     @Query("client_secret") String client_secret,
                                                     @Query("near") String location,
                                                     @Query("limit") int results_number,
                                                     @Query("radius") int radius,
                                                     @Query("v") String version);

    @GET("search")
    Call <Venues> getVenueByLocationCall(@Query("client_id") String client_id,
                                                     @Query("client_secret") String client_secret,
                                                     @Query("near") String location,
                                                     @Query("limit") int results_number,
                                                     @Query("radius") int radius,
                                                     @Query("v") String version);

    @GET("{venue_id}")
    Call<VenueDetails> getVenueDetails(@Path(value = "venue_id", encoded = true) String venue_id,
                                                    @Query("client_id") String client_id,
                                                    @Query("client_secret") String client_secret,
                                                    @Query("v") String version);

}
