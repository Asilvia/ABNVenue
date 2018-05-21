package com.abn.asilvia.abnvenue.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.abn.asilvia.abnvenue.db.dao.VenuesDao;

/**
 * Created by asilvia on 21/05/2018.
 */

@Database(entities = {LocalVenues.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    abstract public VenuesDao venuesDao();
}
