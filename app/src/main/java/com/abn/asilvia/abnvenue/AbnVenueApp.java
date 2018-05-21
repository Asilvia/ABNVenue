package com.abn.asilvia.abnvenue;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;

import com.abn.asilvia.abnvenue.di.component
        .DaggerMainComponent;
import com.abn.asilvia.abnvenue.util.ConnectivityReceiver;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.BroadcastReceiverKey;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import dagger.android.HasBroadcastReceiverInjector;
import timber.log.Timber;

/**
 * Created by asilvia on 18/05/2018.
 * Application class
 */

public class AbnVenueApp extends Application implements HasActivityInjector, HasBroadcastReceiverInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerMainComponent.builder().application(this).build().inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }


    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return broadcastReceiverDispatchingAndroidInjector;
    }
}
