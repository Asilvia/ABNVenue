package com.abn.asilvia.abnvenue.ui.details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;

import com.abn.asilvia.abnvenue.BR;
import com.abn.asilvia.abnvenue.R;
import com.abn.asilvia.abnvenue.databinding.ActivityDetailsBinding;
import com.abn.asilvia.abnvenue.databinding.ActivityMainBinding;
import com.abn.asilvia.abnvenue.db.LocalVenues;
import com.abn.asilvia.abnvenue.ui.base.BaseActivity;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    ActivityDetailsBinding mActivityDetailsBinding;
    private DetailsViewModel detailsViewModel;


    PhotosAdapter adapter;

    String id;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDetailsBinding = getViewDataBinding();
        getParameters();


        detailsViewModel.getVenueDetails(id);



        adapter = new PhotosAdapter(this);

        mActivityDetailsBinding.rvPhotos.setLayoutManager(new GridLayoutManager(this, 2));
        mActivityDetailsBinding.rvPhotos.setHasFixedSize(true);
        mActivityDetailsBinding.rvPhotos.setAdapter(adapter);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);



        detailsViewModel.getObservableVenue().observe(this, new Observer<LocalVenues>() {
            @Override
            public void onChanged(@Nullable LocalVenues venueObject) {
                mActivityDetailsBinding.tvDescription.setText(venueObject.getDescription());
                mActivityDetailsBinding.tvContactInformation.setText(venueObject.getContact_info());
                mActivityDetailsBinding.tvAddress.setText(venueObject.getAddress());
                mActivityDetailsBinding.tvRating.setText(String.valueOf(venueObject.getRating()));
                adapter.setPhotosList(venueObject.getPhotos());



            }
        });



    }

    @Override
    public DetailsViewModel getViewModel() {
        detailsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailsViewModel.class);
        return detailsViewModel;
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
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
