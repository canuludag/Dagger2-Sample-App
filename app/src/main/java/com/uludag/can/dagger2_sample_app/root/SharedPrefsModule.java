package com.uludag.can.dagger2_sample_app.root;

import android.content.Context;
import android.content.SharedPreferences;

import com.uludag.can.dagger2_sample_app.di.NewsApplicationModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NewsApplicationModule.class})
public class SharedPrefsModule {

    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor mPrefsEditor;

    @Singleton
    @Provides
    public SharedPreferences provideSharedPrefs(Context context) {
        // Provides the shared preferences
        mSharedPreferences = context.getSharedPreferences("APP_PREFS", context.MODE_PRIVATE);

        return mSharedPreferences;
    }

    @Singleton
    @Provides
    public SharedPreferences.Editor providePrefsEditor() {
        // Provides shared preferences editor
        mPrefsEditor = mSharedPreferences.edit();

        return mPrefsEditor;
    }

}
