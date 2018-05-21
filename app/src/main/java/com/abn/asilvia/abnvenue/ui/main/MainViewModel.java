package com.abn.asilvia.abnvenue.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.abn.asilvia.abnvenue.db.LocalVenues;
import com.abn.asilvia.abnvenue.repository.DataManager;
import com.abn.asilvia.abnvenue.ui.base.BaseViewModel;
import com.abn.asilvia.abnvenue.util.AbsentLiveData;
import com.abn.asilvia.abnvenue.vo.search.Venue;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by asilvia on 18/05/2018.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> {
    private final DataManager mDataManager;
    LiveData<List<LocalVenues>> mObservableVenuesList;
    MutableLiveData<List<LocalVenues>> updatedVenues = new MediatorLiveData<>();
    List<LocalVenues> savedVenues = new ArrayList<>();
    String mLastLocation;


    public MainViewModel(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
        mObservableVenuesList = AbsentLiveData.create();
        updatedVenues = new MediatorLiveData<>();
    }

    public void setLastLocation(String lastLocation)
    {
        mLastLocation = lastLocation;
        mDataManager.setLastLocation(mLastLocation);
    }

    public String getLastLocation()
    {
        mLastLocation = mDataManager.getLastLocation();
        return mLastLocation;
    }

    void getInVenueList (String location)
    {
        Timber.d("===get venue list");

        mObservableVenuesList = AbsentLiveData.create();
        updatedVenues = new MediatorLiveData<>();

        mObservableVenuesList = Transformations.switchMap(mDataManager.getVenues(location), apiResponse->{

            ArrayList<LocalVenues> list = new ArrayList<>();

            if(apiResponse.isSuccessful())
            {
                for (Venue item:apiResponse.body.getResponse().getVenues())
                {
                    LocalVenues venueObject = new LocalVenues();
                    venueObject.setId(item.getId());
                    venueObject.setTitle(item.getName());
                    String address = "";
                    for (String local:item.getLocation().getFormattedAddress() )
                        address += "\n"+ local;
                    venueObject.setAddress(address);
                    list.add(venueObject);
                }
                Timber.d("=== save venues");
                saveVenues(list);
            }
            else
            {

                Timber.d("=== error");
            }
            updatedVenues.postValue(list);
            return updatedVenues;
        });


    }
    LiveData<List<LocalVenues>>getObservableVenuesList()
    {
        return mObservableVenuesList;
    }



    public List<LocalVenues> getSavedVenues() {
        return mDataManager.getSavedVenues();
    }

    public void setSavedVenues(List<LocalVenues> savedVenues) {
        this.savedVenues = savedVenues;
    }

    private void saveVenues(List<LocalVenues> venues)
    {

        mDataManager.saveVenues(venues);
    }
}
