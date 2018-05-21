package com.abn.asilvia.abnvenue.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.abn.asilvia.abnvenue.BR;
import com.abn.asilvia.abnvenue.R;
import com.abn.asilvia.abnvenue.databinding.ActivityMainBinding;
import com.abn.asilvia.abnvenue.db.LocalVenues;
import com.abn.asilvia.abnvenue.ui.base.BaseActivity;
import com.abn.asilvia.abnvenue.util.ConnectivityReceiver;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    ActivityMainBinding mActivityStartBinding;
    private MainViewModel mainViewModel;

    private MainAdapter adapter;
    ConnectivityReceiver conectivityReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityStartBinding = getViewDataBinding();


        conectivityReceiver = new ConnectivityReceiver();

        adapter = new MainAdapter(this);
        if(mainViewModel.getSavedVenues().size() == 0) {
            mActivityStartBinding.tvLastSearched.setText(R.string.updated_empty);
        }
        else {
            mActivityStartBinding.tvLastSearched.setText(getString(R.string.updated_location) + mainViewModel.getLastLocation());
            adapter.setListData(mainViewModel.getSavedVenues());
        }


        mActivityStartBinding.venues.setHasFixedSize(true);
        mActivityStartBinding.venues.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mActivityStartBinding.venues.setAdapter(adapter);

       // https://api.foursquare.com/v2/venues/58a1a5645cab2f7fd69afe83?client_id=AG2YH1DPWTU0GLCPYXYNP41GRY1QXVWAD0LLSG1S2K3WSV2D&client_secret=FZF0CYTJ4ZAIFNP3URPJSADMLTQPOR2UY0NML4WM0H0C5IYD&v=20180518
       // https://api.foursquare.com/v2/venues/search?client_id=AG2YH1DPWTU0GLCPYXYNP41GRY1QXVWAD0LLSG1S2K3WSV2D&client_secret=FZF0CYTJ4ZAIFNP3URPJSADMLTQPOR2UY0NML4WM0H0C5IYD&near=faro&limit=2&v=20180518
        // https://api.foursquare.com/v2/venues/412d2800f964a520df0c1fe3/photos?client_id=AG2YH1DPWTU0GLCPYXYNP41GRY1QXVWAD0LLSG1S2K3WSV2D&client_secret=FZF0CYTJ4ZAIFNP3URPJSADMLTQPOR2UY0NML4WM0H0C5IYD&v=20180518

        //https://igx.4sqi.net/img/general/100x300//62673616__LEvERvp_4B1lVNAQ2jbjCM2wGCxoMOoSZFmnf6jsvk.jpg





        mActivityStartBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityStartBinding.search.setIconified(false);
            }
        });

        mActivityStartBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mainViewModel.getInVenueList(query);
                mainViewModel.getObservableVenuesList().observe(MainActivity.this, new Observer<List<LocalVenues>>() {
                    @Override
                    public void onChanged(@Nullable List<LocalVenues> venueObjects) {
                        if(venueObjects.isEmpty())
                        {
                            showErrorDialog();
                        }
                        else {
                            mActivityStartBinding.tvLastSearched.setText(getString(R.string.updated_location) + query);
                            mainViewModel.setLastLocation(query);
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

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(conectivityReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
   unregisterReceiver(conectivityReceiver);

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


    public MainViewModel getViewModel() {
        mainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }



}
