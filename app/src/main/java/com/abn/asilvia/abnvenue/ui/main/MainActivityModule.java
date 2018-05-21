package com.abn.asilvia.abnvenue.ui.main;

import android.arch.lifecycle.ViewModelProvider;


import com.abn.asilvia.abnvenue.repository.DataManager;
import com.abn.asilvia.abnvenue.util.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by asilvia on 19-05-2018.
 */


    @Module
    public class MainActivityModule {

        @Provides
        MainViewModel provideStartViewModel(DataManager dataManager) {
            return new MainViewModel(dataManager);
        }

        @Provides
        ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
            return new ViewModelProviderFactory<>(mainViewModel);
        }

    }
