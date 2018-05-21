package com.abn.asilvia.abnvenue.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.abn.asilvia.abnvenue.db.AppDatabase;
import com.abn.asilvia.abnvenue.db.AppDbVenuesHelper;
import com.abn.asilvia.abnvenue.db.DBVenuesHelper;
import com.abn.asilvia.abnvenue.db.DbsInfo;
import com.abn.asilvia.abnvenue.db.dao.VenuesDao;
import com.abn.asilvia.abnvenue.preferences.AppPreferencesHelper;
import com.abn.asilvia.abnvenue.preferences.PreferencesHelper;
import com.abn.asilvia.abnvenue.preferences.PreferencesInfo;
import com.abn.asilvia.abnvenue.repository.AppDataManager;
import com.abn.asilvia.abnvenue.repository.DataManager;
import com.abn.asilvia.abnvenue.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by asilvia on 18/05/2018.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    @DbsInfo.DbInfo
    AppDatabase provideAppDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class,"abnVenues.db").allowMainThreadQueries().build();
    }
    @Singleton
    @Provides
    VenuesDao provideLocalCoinDao(AppDatabase db) {
        return db.venuesDao();
    }

    @Provides
    @Singleton
    DBVenuesHelper provideDbHelper(AppDbVenuesHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @PreferencesInfo.PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREFERENCE_NAME;
    }
    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
}
