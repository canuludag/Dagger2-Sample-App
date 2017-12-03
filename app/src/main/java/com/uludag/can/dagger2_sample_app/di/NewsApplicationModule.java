package com.uludag.can.dagger2_sample_app.di;

import android.content.Context;

import com.uludag.can.dagger2_sample_app.root.NewsApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsApplicationModule {

    public NewsApplication mNewsApplication;

    public NewsApplicationModule(NewsApplication newsApplication) {
        mNewsApplication = newsApplication;
    }

    @Singleton
    @Provides
    public NewsApplication provideApp() {
        // Provides the application
        return mNewsApplication;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        // Provides the context
        return mNewsApplication.getApplicationContext();
    }

}
