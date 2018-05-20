package com.abn.asilvia.abnvenue.repository;

import android.arch.lifecycle.LiveData;

import com.abn.asilvia.abnvenue.BuildConfig;
import com.abn.asilvia.abnvenue.api.ApiResponse;
import com.abn.asilvia.abnvenue.api.MainApiHelper;
import com.abn.asilvia.abnvenue.util.AppUtils;
import com.abn.asilvia.abnvenue.vo.details.VenueDetails;
import com.abn.asilvia.abnvenue.vo.search.Venues;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by asilvia on 18/05/2018.
 */

public class AppDataManager implements DataManager {

    private MainApiHelper mMainApiHelper;


    @Inject
    public AppDataManager(MainApiHelper mMainApiHelper) {
        this.mMainApiHelper = mMainApiHelper;
    }


    @Override
    public LiveData<ApiResponse<Venues>> getVenues(String location) {
        Timber.d("=== entra en getVenues");
         return mMainApiHelper.getVenueByLocation(BuildConfig.FoursquareClientId,
                BuildConfig.FoursquareClientSecret,
                location,
                BuildConfig.Limit,
                BuildConfig.Radius,
                AppUtils.getApiVersion());
    }

    @Override
    public LiveData<ApiResponse<VenueDetails>> getVenueDetail(String venue_id) {
        return mMainApiHelper.getVenueDetails(venue_id,
                BuildConfig.FoursquareClientId,
                BuildConfig.FoursquareClientSecret,
                AppUtils.getApiVersion());
    }
}
