package com.abn.asilvia.abnvenue.ui.details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.abn.asilvia.abnvenue.R;
import com.abn.asilvia.abnvenue.ui.main.MainViewModel;
import com.abn.asilvia.abnvenue.vo.VenueObject;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

public class DetailsActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private DetailsViewModel detailsViewModel;

    TextView description;
    TextView contactInformation;
    TextView address;
    TextView rating;
    RecyclerView photos;

    PhotosAdapter adapter;

    String id;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getParameters();
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        detailsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailsViewModel.class);
        setContentView(R.layout.activity_details);
        detailsViewModel.getVenueDetails(id);

        description = (TextView) findViewById(R.id.tvDescription);
        contactInformation = (TextView)findViewById(R.id.tvContactInformation);
        address = (TextView)findViewById(R.id.tvAddress);
        rating = (TextView)findViewById(R.id.tvRating);
        photos = (RecyclerView) findViewById(R.id.rvPhotos);

        adapter = new PhotosAdapter(this);
        photos.setLayoutManager(new GridLayoutManager(this, 2));
        photos.setHasFixedSize(true);
        photos.setAdapter(adapter);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);



        detailsViewModel.getObservableVenue().observe(this, new Observer<VenueObject>() {
            @Override
            public void onChanged(@Nullable VenueObject venueObject) {
                description.setText(venueObject.getDescription());
                contactInformation.setText(venueObject.getContact_info());
                address.setText(venueObject.getAddress());
                rating.setText(String.valueOf(venueObject.getRating()));
                adapter.setPhotosList(venueObject.getPhotos());



            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    private void getParameters() {
        Intent intent = getIntent();
        if(intent!= null)
        {
            id = intent.getStringExtra("id");
            title = intent.getStringExtra("title");
        }
    }
}
