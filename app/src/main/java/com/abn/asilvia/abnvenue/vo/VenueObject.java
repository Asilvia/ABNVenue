package com.abn.asilvia.abnvenue.vo;

import java.util.ArrayList;

/**
 * Created by asilvia on 20/05/2018.
 */

public class VenueObject {
    String id;
    String title;
    String address;
    String description;
    String contact_info;
    double rating;
    ArrayList<String> photos;

    public VenueObject(String id, String title, String address) {
        this.id = id;
        this.title = title;
        this.address = address;
        photos = new ArrayList<>();
    }

    public VenueObject(String id, String title, String address, String description, String contact_info, double rating,  ArrayList<String> photos) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.description = description;
        this.contact_info = contact_info;
        this.rating = rating;
        this.photos.addAll(photos);
    }

    public VenueObject() {

        this.id = "";
        this.title = "";
        this.address = "No address specified by the author of the venue";
        this.description = "No description specified by the author of the venue ";
        this.contact_info = "No contact information specified by the author of the venue";
        this.rating = 0;
        this.photos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public  ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos( ArrayList<String> photos) {
        this.photos = photos;
    }
}
