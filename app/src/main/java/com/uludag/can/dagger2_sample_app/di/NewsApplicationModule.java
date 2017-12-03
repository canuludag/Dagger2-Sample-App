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
        return mNewsApplication;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mNewsApplication.getApplicationContext();
    }

}
