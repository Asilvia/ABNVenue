package com.abn.asilvia.abnvenue.db;

import android.arch.lifecycle.LiveData;

import com.abn.asilvia.abnvenue.vo.VenueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asilvia on 21/05/2018.
 */

public interface DBVenuesHelper {

        void saveVenue(LocalVenues localVenues);
        List<LocalVenues> getSavedVenues();
        void  saveVenuesList(List<LocalVenues> list);
        LiveData<LocalVenues> findVenueById(String id);
        void updateVenues(List<LocalVenues> list);
        void deleteVenue(LocalVenues localVenues);
        void deleteAllVenue();

}
