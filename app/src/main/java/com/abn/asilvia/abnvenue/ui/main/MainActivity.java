package com.abn.asilvia.abnvenue.ui.main;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;


import com.abn.asilvia.abnvenue.R;
import com.abn.asilvia.abnvenue.vo.VenueObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private MainViewModel mainViewModel;
    private MainAdapter adapter;
    RecyclerView venuesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        mainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        setContentView(R.layout.activity_main);
        venuesList = (RecyclerView)findViewById(R.id.venues);
        venuesList.setHasFixedSize(true);
        venuesList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new MainAdapter(this);
        venuesList.setAdapter(adapter);
       // https://api.foursquare.com/v2/venues/58a1a5645cab2f7fd69afe83?client_id=AG2YH1DPWTU0GLCPYXYNP41GRY1QXVWAD0LLSG1S2K3WSV2D&client_secret=FZF0CYTJ4ZAIFNP3URPJSADMLTQPOR2UY0NML4WM0H0C5IYD&v=20180518
       // https://api.foursquare.com/v2/venues/search?client_id=AG2YH1DPWTU0GLCPYXYNP41GRY1QXVWAD0LLSG1S2K3WSV2D&client_secret=FZF0CYTJ4ZAIFNP3URPJSADMLTQPOR2UY0NML4WM0H0C5IYD&near=faro&limit=2&v=20180518
        // https://api.foursquare.com/v2/venues/412d2800f964a520df0c1fe3/photos?client_id=AG2YH1DPWTU0GLCPYXYNP41GRY1QXVWAD0LLSG1S2K3WSV2D&client_secret=FZF0CYTJ4ZAIFNP3URPJSADMLTQPOR2UY0NML4WM0H0C5IYD&v=20180518

        //https://igx.4sqi.net/img/general/100x300//62673616__LEvERvp_4B1lVNAQ2jbjCM2wGCxoMOoSZFmnf6jsvk.jpg




        final SearchView searchView = (SearchView)findViewById(R.id.search);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mainViewModel.getInVenueList(query);
                mainViewModel.getObservableVenuesList().observe(MainActivity.this, new Observer<List<VenueObject>>() {
                    @Override
                    public void onChanged(@Nullable List<VenueObject> venueObjects) {
                        if(venueObjects.isEmpty())
                        {
                            showErrorDialog();
                        }
                        else {
                            adapter.setListData(venueObjects);
                        }
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                return false;
            }
        });





        //https://api.foursquare.com/v2/venues/search?client_id=AG2YH1DPWTU0GLCPYXYNP41GRY1QXVWAD0LLSG1S2K3WSV2D&client_secret=FZF0CYTJ4ZAIFNP3URPJSADMLTQPOR2UY0NML4WM0H0C5IYD&near=faro&v=20180518
    }

    private void showErrorDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        alert.setTitle(R.string.alert_title);
        alert.setMessage(R.string.alert_body);
        alert.setPositiveButton(R.string.alert_positive, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.create().show();
    }

}
