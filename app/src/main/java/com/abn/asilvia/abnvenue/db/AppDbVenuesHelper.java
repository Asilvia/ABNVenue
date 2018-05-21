package com.abn.asilvia.abnvenue.db;

import android.arch.lifecycle.LiveData;

import com.abn.asilvia.abnvenue.db.dao.VenuesDao;
import com.abn.asilvia.abnvenue.vo.VenueObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by asilvia on 21/05/2018.
 */

@Singleton
public class AppDbVenuesHelper implements DBVenuesHelper {

    private final AppDatabase mAppDatabase;
    private final VenuesDao mVenuesDao;

    @Inject
    public AppDbVenuesHelper(@DbsInfo.DbInfo AppDatabase appDatabase)
    {
        mAppDatabase = appDatabase;
        mVenuesDao = mAppDatabase.venuesDao();
    }

    @Override
    public void saveVenue(LocalVenues localVenues) {
        mVenuesDao.insert(localVenues);
    }

    @Override
    public List<LocalVenues> getSavedVenues() {
        return mVenuesDao.findAllVenues();
    }

    @Override
    public void saveVenuesList(List<LocalVenues> list) {
        mVenuesDao.saveVenueList(list);
    }

    @Override
    public LiveData<LocalVenues> findVenueById(String id) {
        return mVenuesDao.findVenueById(id);
    }

    @Override
    public void updateVenues(List<LocalVenues> list) {
        mVenuesDao.update(list);
    }

    @Override
    public void deleteVenue(LocalVenues localVenues) {
        mVenuesDao.deleteVenue(localVenues);
    }

    @Override
    public void deleteAllVenue() {
        mVenuesDao.deleteAllVenues();
    }
}
