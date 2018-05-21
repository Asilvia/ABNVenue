package com.abn.asilvia.abnvenue.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.abn.asilvia.abnvenue.repository.DataManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by asilvia on 21/05/2018.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

    @Inject
    DataManager mDataManager;

        public ConnectivityReceiver() {
            super();

        }

        @Override
        public void onReceive(Context context, Intent arg1) {
            AndroidInjection.inject(this, context);
            ConnectivityManager cm = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();

            if(isConnected)
            {
                mDataManager.updateVenues();
            }
        }

    }
