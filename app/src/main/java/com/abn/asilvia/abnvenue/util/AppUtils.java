package com.abn.asilvia.abnvenue.util;

import android.arch.persistence.room.TypeConverter;

import com.abn.asilvia.abnvenue.db.LocalVenues;
import com.abn.asilvia.abnvenue.vo.VenueObject;
import com.abn.asilvia.abnvenue.vo.details.Contact;
import com.abn.asilvia.abnvenue.vo.details.Group;
import com.abn.asilvia.abnvenue.vo.details.Item;
import com.abn.asilvia.abnvenue.vo.details.Photos;
import com.abn.asilvia.abnvenue.vo.details.Venue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public static ArrayList<String> getphotos(LocalVenues venueObject, Venue venueItem) {

        ArrayList<String> photosUrl = new ArrayList<>();
        if(venueItem.getPhotos() != null)
        {
            Photos photos = venueItem.getPhotos();
            for (Group group: photos.getGroups())
            {
                for(Item item:group.getItems())
                {
                    Timber.d(" id=== "+ venueItem.getId());
                    String photoUrl =  item.getPrefix() + item.getWidth()+"x"+ item.getHeight()+item.getSuffix();
                    Timber.d(" photo=== "+ photoUrl);
                    photosUrl.add(photoUrl);
                }
            }

        }
        return photosUrl;
    }

    public static String getContact(LocalVenues venueObject, Venue venueItem) {
        String contactText = "";
        if(venueItem.getContact() != null)
        {
            Contact contact = venueItem.getContact();
            if(contact.getFacebookName() !=null) contactText +=  contact.getFacebookName() + "\n";
            if(contact.getInstagram() !=null) contactText += contact.getInstagram() + "\n";
            if(contact.getFormattedPhone() !=null) contactText +=  contact.getFormattedPhone() + "\n";
            if(contact.getTwitter()!=null) contactText += contact.getTwitter() + "\n";
        }
        Timber.d("==== contactText: " + contactText);
        return contactText;
    }

    /*
        Get Description
     */
    public static String getDescription(LocalVenues venueObject, Venue venueItem) {
        String direction="";
        if(venueItem.getDescription() != null)
        {
            direction = venueItem.getDescription();
        }
        Timber.d("==== getDescription: " + direction);
        return direction;
    }

    public static double getRating(LocalVenues venueObject, Venue venueItem) {
        double rating=0;
        if(venueItem.getRating() != null)
        {
            rating = venueItem.getRating();
        }
        Timber.d("==== rating: " + rating);
        return rating;
    }

    public static String getAddress(LocalVenues venueObject, Venue venueItem) {
        String address = "";
        if(venueItem.getLocation().getFormattedAddress() != null && venueItem.getLocation().getFormattedAddress().size()>0) {
            for (String local : venueItem.getLocation().getFormattedAddress())
                address += "\n" + local;
        }
        Timber.d("==== address: " + address);
        return address;
    }




}
