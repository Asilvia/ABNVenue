package com.abn.asilvia.abnvenue.ui.details;

import android.arch.lifecycle.LiveData;

import com.abn.asilvia.abnvenue.db.LocalVenues;
import com.abn.asilvia.abnvenue.repository.DataManager;
import com.abn.asilvia.abnvenue.ui.base.BaseViewModel;
import com.abn.asilvia.abnvenue.util.AbsentLiveData;

/**
 * Created by asilvia on 18/05/2018.
 */

public class DetailsViewModel extends BaseViewModel<DetailsNavigator> {


    private final DataManager mDataManager;
    LiveData<LocalVenues> mObservableVenue;



    public DetailsViewModel(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
        this.mObservableVenue = AbsentLiveData.create();
    }

    void getVenueDetails (String venue_id)
    {
        mObservableVenue = mDataManager.findVenue(venue_id);

    }


    LiveData<LocalVenues> getObservableVenue()
    {
        return mObservableVenue;
    }




}
