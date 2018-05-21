package com.abn.asilvia.abnvenue.db.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abn.asilvia.abnvenue.db.LocalVenues;
import com.abn.asilvia.abnvenue.vo.VenueObject;

import java.util.List;

/**
 * Created by asilvia on 21/05/2018.
 */
@Dao
public interface VenuesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LocalVenues localVenues);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(List<LocalVenues> list);


    @Query("SELECT * FROM LocalVenues WHERE id = :id")
    LiveData<LocalVenues> findVenueById(String id);

    @Query("SELECT * FROM LocalVenues")
    List<LocalVenues> findAllVenues();

    @Delete
    public void deleteVenue (LocalVenues localVenues);

    @Query("DELETE FROM LocalVenues")
    public void deleteAllVenues();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveVenueList(List<LocalVenues> list);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(LocalVenues localVenues);
}
