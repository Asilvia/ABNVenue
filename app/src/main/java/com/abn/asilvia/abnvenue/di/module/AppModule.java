package com.abn.asilvia.abnvenue.di.module;

import android.app.Application;
import android.content.Context;

import com.abn.asilvia.abnvenue.repository.AppDataManager;
import com.abn.asilvia.abnvenue.repository.DataManager;

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

}
