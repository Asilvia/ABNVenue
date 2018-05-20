package com.abn.asilvia.abnvenue.ui.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;


import com.abn.asilvia.abnvenue.R;
import com.abn.asilvia.abnvenue.api.ApiResponse;
import com.abn.asilvia.abnvenue.repository.DataManager;
import com.abn.asilvia.abnvenue.util.AbsentLiveData;
import com.abn.asilvia.abnvenue.vo.VenueObject;
import com.abn.asilvia.abnvenue.vo.details.Contact;
import com.abn.asilvia.abnvenue.vo.details.Group;
import com.abn.asilvia.abnvenue.vo.details.Item;
import com.abn.asilvia.abnvenue.vo.details.Photos;
import com.abn.asilvia.abnvenue.vo.details.Venue;
import com.abn.asilvia.abnvenue.vo.details.VenueDetails;

import timber.log.Timber;

/**
 * Created by asilvia on 18/05/2018.
 */

public class DetailsViewModel extends ViewModel {
    private final DataManager mDataManager;



    LiveData<VenueObject> mObservableVenue;
    MutableLiveData<VenueObject> updatedVenues = new MediatorLiveData<>();



    public DetailsViewModel(DataManager mDataManager) {
        this.mDataManager = mDataManager;
        mObservableVenue = AbsentLiveData.create();
        updatedVenues = new MediatorLiveData<>();
    }

    void getVenueDetails (String venue_id)
    {
        Timber.d("===get venue list");

        mObservableVenue = AbsentLiveData.create();
        updatedVenues = new MediatorLiveData<>();

        mObservableVenue = Transformations.switchMap(mDataManager.getVenueDetail(venue_id), detailsApi->{


            VenueObject venueObject = new VenueObject();
            if(detailsApi.isSuccessful())
            {
                getVenueObject(detailsApi, venueObject);
            }
            else
            {

                Timber.d("=== error");
            }
            updatedVenues.postValue(venueObject);
            return updatedVenues;
        });


    }

    private void getVenueObject(ApiResponse<VenueDetails> detailsApi, VenueObject venueObject) {
        Venue venueItem = detailsApi.body.getResponse().getVenue();
        venueObject.setId(venueItem.getId());
        venueObject.setTitle(venueItem.getName());
        getAddress(venueObject, venueItem);
        getRating(venueObject, venueItem);
        getDescription(venueObject, venueItem);
        getContact(venueObject, venueItem);
        getphotos(venueObject, venueItem);
    }

    private void getphotos(VenueObject venueObject, Venue venueItem) {
        if(venueItem.getPhotos() != null)
        {
                Photos photos = venueItem.getPhotos();
                for (Group group: photos.getGroups())
                {
                    for(Item item:group.getItems())
                    {
                        Timber.d(" id=== "+ venueItem.getId());
                        String photoUrl =  item.getPrefix() + item.getWidth()+"x"+ item.getHeight()+item.getSuffix();
                        venueObject.getPhotos().add(photoUrl);
                    }
                }
        }
    }

    private void getContact(VenueObject venueObject, Venue venueItem) {
        if(venueItem.getContact() != null)
        {
            String contactText = "";
            Contact contact = venueItem.getContact();
            if(contact.getFacebookName() !=null) contactText +=  contact.getFacebookName() + "\n";
            if(contact.getInstagram() !=null) contactText += contact.getInstagram() + "\n";
            if(contact.getFormattedPhone() !=null) contactText +=  contact.getFormattedPhone() + "\n";
            if(contact.getTwitter()!=null) contactText += contact.getTwitter() + "\n";

            if(contactText.length() > 0) venueObject.setContact_info(contactText);
        }
    }

    private void getDescription(VenueObject venueObject, Venue venueItem) {
        if(venueItem.getDescription() != null)
        {
            venueObject.setDescription(venueItem.getDescription());
        }
    }

    private void getRating(VenueObject venueObject, Venue venueItem) {
        if(venueItem.getRating() != null)
        {
            venueObject.setRating(venueItem.getRating());
        }
    }

    private void getAddress(VenueObject venueObject, Venue venueItem) {
        if(venueItem.getLocation().getFormattedAddress() != null && venueItem.getLocation().getFormattedAddress().size()>0) {
            String address = "";
            for (String local : venueItem.getLocation().getFormattedAddress())
                address += "\n" + local;
            venueObject.setAddress(address);
        }
    }

    LiveData<VenueObject>getObservableVenue()
    {
        return mObservableVenue;
    }




}
