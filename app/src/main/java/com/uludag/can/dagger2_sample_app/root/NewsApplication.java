package com.uludag.can.dagger2_sample_app.root;

import android.app.Application;

import com.uludag.can.dagger2_sample_app.di.DaggerNewsApplicationComponent;
import com.uludag.can.dagger2_sample_app.di.NewsApplicationComponent;
import com.uludag.can.dagger2_sample_app.di.NewsApplicationModule;
import com.uludag.can.dagger2_sample_app.networking.NewsApiModule;

public class NewsApplication extends Application {

    private NewsApplicationComponent mNewsApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Dagger App Component and add modules
        mNewsApplicationComponent = DaggerNewsApplicationComponent.builder()
                .newsApplicationModule(new NewsApplicationModule(this))
                .sharedPrefsModule(new SharedPrefsModule())
                .newsApiModule(new NewsApiModule())
                .build();
    }

    public NewsApplicationComponent getNewsApplicationComponent() {
        return mNewsApplicationComponent;
    }
}
