package com.abn.asilvia.abnvenue.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by asilvia on 18/05/2018.
 */

public  class AppUtils {

    private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

    public static String getApiVersion(){

        Calendar calendar = Calendar.getInstance();
        String strDate = sdfDate.format(calendar.getTime());
        Timber.d("version: " + strDate);
        return strDate;

    }




}
