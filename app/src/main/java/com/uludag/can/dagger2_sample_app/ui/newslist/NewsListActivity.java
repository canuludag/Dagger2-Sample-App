package com.uludag.can.dagger2_sample_app.ui.newslist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.uludag.can.dagger2_sample_app.R;
import com.uludag.can.dagger2_sample_app.model.Article;
import com.uludag.can.dagger2_sample_app.root.NewsApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListActivity extends AppCompatActivity {

    @BindView(R.id.news_list_container)
    RelativeLayout newsListContainer;
    @BindView(R.id.newslist_recyclerview)
    RecyclerView newsListRecyclerView;
    @BindView(R.id.news_list_progressbar)
    ProgressBar newsListProgressbar;

    @Inject
    Context mContext;

    private List<Article> mArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        // Initialize injection for this activity
        ((NewsApplication) getApplication()).getNewsApplicationComponent().inject(this);

        setupRecyclerView();
        setAdapterForRecyclerView();
        getArticles();

    }

    private void setupRecyclerView() {
        newsListRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        newsListRecyclerView.setHasFixedSize(true);
    }


    private void setAdapterForRecyclerView() {

        NewsListAdapter newsListAdapter = new NewsListAdapter(mArticles);
        newsListRecyclerView.setAdapter(newsListAdapter);

    }

    private void getArticles() {

        // Initialize empty list of articles
        mArticles = new ArrayList<>();


    }
}
