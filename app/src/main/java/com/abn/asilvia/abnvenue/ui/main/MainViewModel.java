package com.abn.asilvia.abnvenue.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;


import com.abn.asilvia.abnvenue.repository.DataManager;
import com.abn.asilvia.abnvenue.util.AbsentLiveData;
import com.abn.asilvia.abnvenue.vo.VenueObject;
import com.abn.asilvia.abnvenue.vo.search.Venue;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by asilvia on 18/05/2018.
 */

public class MainViewModel extends ViewModel {
    private final DataManager mDataManager;
    private boolean mIsLoading = false;



    LiveData<List<VenueObject>> mObservableVenuesList;
    MutableLiveData<List<VenueObject>> updatedVenues = new MediatorLiveData<>();


    public MainViewModel(DataManager mDataManager) {
        this.mDataManager = mDataManager;
        mObservableVenuesList = AbsentLiveData.create();
        updatedVenues = new MediatorLiveData<>();
    }

    void getInVenueList (String location)
    {
        Timber.d("===get venue list");

        mObservableVenuesList = AbsentLiveData.create();
        updatedVenues = new MediatorLiveData<>();

        mObservableVenuesList = Transformations.switchMap(mDataManager.getVenues(location), apiResponse->{

            ArrayList<VenueObject> list = new ArrayList<>();

            if(apiResponse.isSuccessful())
            {
               for (Venue item:apiResponse.body.getResponse().getVenues())
               {
                   String id = item.getId();
                   String title = item.getName();
                   String address = "";
                   for (String local:item.getLocation().getFormattedAddress() )
                       address += "\n"+ local;

                   VenueObject venueObject = new VenueObject(id, title,address);
                   list.add(venueObject);
               }

            }
            else
            {

                Timber.d("=== error");
            }
            updatedVenues.postValue(list);
            return updatedVenues;
        });


    }
    LiveData<List<VenueObject>>getObservableVenuesList()
    {
        return mObservableVenuesList;
    }





    public boolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading= isLoading;
    }
}
