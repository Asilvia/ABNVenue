package com.abn.asilvia.abnvenue.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.abn.asilvia.abnvenue.BuildConfig;
import com.abn.asilvia.abnvenue.api.ApiResponse;
import com.abn.asilvia.abnvenue.api.MainApiHelper;
import com.abn.asilvia.abnvenue.db.DBVenuesHelper;
import com.abn.asilvia.abnvenue.db.LocalVenues;
import com.abn.asilvia.abnvenue.preferences.PreferencesHelper;
import com.abn.asilvia.abnvenue.util.AppUtils;
import com.abn.asilvia.abnvenue.vo.VenueObject;
import com.abn.asilvia.abnvenue.vo.details.Contact;
import com.abn.asilvia.abnvenue.vo.details.Group;
import com.abn.asilvia.abnvenue.vo.details.Item;
import com.abn.asilvia.abnvenue.vo.details.Photos;
import com.abn.asilvia.abnvenue.vo.details.Venue;
import com.abn.asilvia.abnvenue.vo.details.VenueDetails;
import com.abn.asilvia.abnvenue.vo.search.Venues;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by asilvia on 18/05/2018.
 */

public class AppDataManager implements DataManager {

    private MainApiHelper mMainApiHelper;
    private DBVenuesHelper mDBVenuesHelper;
    private PreferencesHelper mPreferenceHelper;


    @Inject
    public AppDataManager(MainApiHelper mainApiHelper, DBVenuesHelper dbVenuesHelper, PreferencesHelper preferencesHelper) {
        this.mMainApiHelper = mainApiHelper;
        this.mDBVenuesHelper = dbVenuesHelper;
        this.mPreferenceHelper = preferencesHelper;
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
    public Call<Venues> getVenuesCall(String location) {
          return mMainApiHelper.getVenueByLocationCall(BuildConfig.FoursquareClientId,
                BuildConfig.FoursquareClientSecret,
                location,
                BuildConfig.Limit,
                BuildConfig.Radius,
                AppUtils.getApiVersion());
    }

    @Override
    public Call<VenueDetails> getVenueDetail(String venue_id) {
        return mMainApiHelper.getVenueDetails(venue_id,
                BuildConfig.FoursquareClientId,
                BuildConfig.FoursquareClientSecret,
                AppUtils.getApiVersion());
    }

    @Override
    public List<LocalVenues> getSavedVenues() {
        return mDBVenuesHelper.getSavedVenues();
    }

    @Override
    public void deleteAllVenues() {
        mDBVenuesHelper.deleteAllVenue();
    }

    @Override
    public void saveVenues(List<LocalVenues> list) {
        Timber.d("=== 1: saveVenues: ");
        deleteAllVenues();
        for(LocalVenues localVenue: list)
        {
            final LocalVenues venue = localVenue;
            Timber.d("==== save item: "+localVenue.getId());

            Call<VenueDetails> teste = getVenueDetail(localVenue.getId());
            teste.enqueue(new Callback<VenueDetails>() {
                @Override
                public void onResponse(Call<VenueDetails> call, Response<VenueDetails> response) {
                    if(response.isSuccessful())
                    {
                        Timber.d("=== get value");
                        getVenueObject(response.body(), venue);
                        Timber.d("=== save value");
                        saveVenue(venue);
                    }
                }

                @Override
                public void onFailure(Call<VenueDetails> call, Throwable t) {
                    Timber.d("=== error");
                }
            });


        }
        mDBVenuesHelper.saveVenuesList(list);
    }

    @Override
    public void saveVenue(LocalVenues localVenue) {
        mDBVenuesHelper.saveVenue(localVenue);
    }

    @Override
    public  LiveData<LocalVenues> findVenue(String id) {
        return mDBVenuesHelper.findVenueById(id);
    }

    @Override
    public String getLastLocation() {
        return mPreferenceHelper.getLastSearchLocation();
    }

    @Override
    public void setLastLocation(String location) {
        mPreferenceHelper.setLastSearchLocation(location);
    }

    @Override
    public void updateVenues() {
        if (mPreferenceHelper.getLastSearchLocation().length() > 0) {
            Call<Venues> venuesCall = getVenuesCall(mPreferenceHelper.getLastSearchLocation());
            venuesCall.enqueue(new Callback<Venues>() {
                @Override
                public void onResponse(Call<Venues> call, Response<Venues> response) {
                    ArrayList<LocalVenues> list = new ArrayList<>();

                    if (response.isSuccessful()) {
                        for (com.abn.asilvia.abnvenue.vo.search.Venue item : response.body().getResponse().getVenues()) {
                            LocalVenues venueObject = new LocalVenues();
                            venueObject.setId(item.getId());
                            venueObject.setTitle(item.getName());
                            String address = "";
                            for (String local : item.getLocation().getFormattedAddress())
                                address += "\n" + local;
                            venueObject.setAddress(address);
                            list.add(venueObject);
                        }
                        Timber.d("=== save venues");
                        saveVenues(list);
                    }
                }

                @Override
                public void onFailure(Call<Venues> call, Throwable t) {

                }
            });
        }
    }

    private LocalVenues getVenueObject(VenueDetails detailsApi, LocalVenues venueObject) {

        Venue venueItem = detailsApi.getResponse().getVenue();
        venueObject.setRating(AppUtils.getRating(venueObject, venueItem));
        venueObject.setDescription(AppUtils.getDescription(venueObject, venueItem));
        venueObject.setContact_info(AppUtils.getContact(venueObject, venueItem));
        venueObject.setPhotos(AppUtils.getphotos(venueObject, venueItem));
        return venueObject;
    }


}
