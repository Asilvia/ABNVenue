package com.abn.asilvia.abnvenue.db;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by asilvia on 21/05/2018.
 */

public class DbsInfo {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DbInfo {

    }
}
