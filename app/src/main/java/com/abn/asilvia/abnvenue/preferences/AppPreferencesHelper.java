package com.abn.asilvia.abnvenue.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.abn.asilvia.abnvenue.util.AppConstants;

import javax.inject.Inject;

/**
 * Created by asilvia on 21/05/2018.
 */

public class AppPreferencesHelper  implements PreferencesHelper{

    private final SharedPreferences mPrefs;



    @Inject
    public AppPreferencesHelper(Context context,
                                @PreferencesInfo.PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }
    @Override
    public String getLastSearchLocation() {
        return mPrefs.getString(AppConstants.LOCATION, "");
    }
    @Override
    public void setLastSearchLocation(String location) {
        mPrefs.edit().putString(AppConstants.LOCATION, location).apply();
    }
}
