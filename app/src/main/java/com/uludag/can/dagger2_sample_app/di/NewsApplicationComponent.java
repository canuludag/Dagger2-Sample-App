package com.uludag.can.dagger2_sample_app.di;

import com.uludag.can.dagger2_sample_app.networking.NewsApiModule;
import com.uludag.can.dagger2_sample_app.root.SharedPrefsModule;
import com.uludag.can.dagger2_sample_app.ui.login.LoginActivity;
import com.uludag.can.dagger2_sample_app.ui.newslist.NewsListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NewsApplicationModule.class, SharedPrefsModule.class, NewsApiModule.class})
public interface NewsApplicationComponent {

    // Injection targets
    void inject(NewsListActivity target);
    void inject(LoginActivity target);

}
