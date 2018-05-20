package com.abn.asilvia.abnvenue.ui.details;

import android.arch.lifecycle.ViewModelProvider;


import com.abn.asilvia.abnvenue.repository.DataManager;
import com.abn.asilvia.abnvenue.util.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by asilvia on 19-05-2018.
 */


    @Module
    public class DetailsActivityModule {

        @Provides
        DetailsViewModel provideStartViewModel(DataManager dataManager) {
            return new DetailsViewModel(dataManager);
        }

        @Provides
        ViewModelProvider.Factory detailsViewModelProvider(DetailsViewModel detailsViewModel) {
            return new ViewModelProviderFactory<>(detailsViewModel);
        }

    }
