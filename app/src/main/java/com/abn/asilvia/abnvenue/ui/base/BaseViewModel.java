package com.abn.asilvia.abnvenue.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.abn.asilvia.abnvenue.repository.DataManager;


/**
 * Created by asilvia on 26-10-2017.
 */

public abstract class BaseViewModel<N> extends ViewModel {
    private N mNavigator;
    private final DataManager mDataManager;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    public BaseViewModel(DataManager dataManager) {
        this.mDataManager = dataManager;
    }


    public void onViewCreated() {

    }

    public void onDestroyView() {

    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }


    public N getNavigator() {
        return mNavigator;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

}
