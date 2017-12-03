package com.uludag.can.dagger2_sample_app.di;

import com.uludag.can.dagger2_sample_app.ui.newslist.NewsListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NewsApplicationModule.class})
public interface NewsApplicationComponent {

    void inject(NewsListActivity target);

}
