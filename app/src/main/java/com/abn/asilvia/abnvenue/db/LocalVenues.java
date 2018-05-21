package com.abn.asilvia.abnvenue.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by asilvia on 21/05/2018.
 */

@Entity(tableName = "LocalVenues")
public class LocalVenues {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo (name = "address")
    private String address;
    @ColumnInfo (name = "description")
    private String description;
    @ColumnInfo (name = "contact_info")
    private String contact_info;
    @ColumnInfo (name = "rating")
    private double rating;
    @ColumnInfo (name = "photos")
    private ArrayList<String> photos = new ArrayList<>();


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {

        this.photos = photos;
    }

}
