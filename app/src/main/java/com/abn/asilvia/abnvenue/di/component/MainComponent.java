package com.abn.asilvia.abnvenue.di.component;

import android.app.Application;

import com.abn.asilvia.abnvenue.AbnVenueApp;
import com.abn.asilvia.abnvenue.di.module.ActivityModule;
import com.abn.asilvia.abnvenue.di.module.AppModule;
import com.abn.asilvia.abnvenue.di.module.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by asilvia on 18/05/2018.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class,  NetModule.class, AppModule.class, ActivityModule.class})
public interface MainComponent
{
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);


        MainComponent build();
    }
    void inject(AbnVenueApp app);
}
