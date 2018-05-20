package com.abn.asilvia.abnvenue.di.module;

import com.abn.asilvia.abnvenue.ui.details.DetailsActivity;
import com.abn.asilvia.abnvenue.ui.details.DetailsActivityModule;
import com.abn.asilvia.abnvenue.ui.main.MainActivity;
import com.abn.asilvia.abnvenue.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by asilvia on 19/05/2018.
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {DetailsActivityModule.class})
    abstract DetailsActivity contributeDetailsActivity();
}
