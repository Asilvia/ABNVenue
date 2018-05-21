package com.abn.asilvia.abnvenue.preferences;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by asilvia on 21/05/2018.
 */

public class PreferencesInfo {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PreferenceInfo {

    }
}
