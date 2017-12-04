package com.uludag.can.dagger2_sample_app.ui.newslist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.uludag.can.dagger2_sample_app.R;
import com.uludag.can.dagger2_sample_app.listeners.NewsListCardOnClickListener;
import com.uludag.can.dagger2_sample_app.model.Article;
import com.uludag.can.dagger2_sample_app.model.ArticlesResponse;
import com.uludag.can.dagger2_sample_app.networking.NewsApiService;
import com.uludag.can.dagger2_sample_app.root.NewsApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsListActivity extends AppCompatActivity implements NewsListCardOnClickListener{

    @BindView(R.id.news_list_container)
    RelativeLayout newsListContainer;
    @BindView(R.id.newslist_recyclerview)
    RecyclerView newsListRecyclerView;
    @BindView(R.id.news_list_progressbar)
    ProgressBar newsListProgressbar;

    @Inject
    Context mContext;
    @Inject
    NewsApiService mNewsApiService;

    private List<Article> mArticles;
    private NewsListAdapter mNewsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        // Initialize injection for this activity
        ((NewsApplication) getApplication()).getNewsApplicationComponent().inject(this);

        setupRecyclerView();
        getArticles();

    }

    private void setupRecyclerView() {
        newsListRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        newsListRecyclerView.setHasFixedSize(true);
    }


    private void setAdapterForRecyclerView(List<Article> articles) {

        mNewsListAdapter = new NewsListAdapter(articles, this);
        newsListRecyclerView.setAdapter(mNewsListAdapter);

    }

    private void getArticles() {

        mNewsApiService.getArticles("techcrunch").enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                ArticlesResponse articlesResponse = response.body();

                if (articlesResponse != null) {
                    mArticles = articlesResponse.getArticles();
                    setAdapterForRecyclerView(mArticles);
                    newsListProgressbar.setVisibility(View.GONE);
                } else {
                    Snackbar errorSnackBar = createSnackBar("An error occured");
                    errorSnackBar.setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getArticles();
                        }
                    });

                    errorSnackBar.show();
                }

            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                Snackbar errorSnackBar = createSnackBar("An error occured");
                errorSnackBar.setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getArticles();
                    }
                });

                errorSnackBar.show();
            }
        });

    }

    private Snackbar createSnackBar(String message) {
        return Snackbar.make(newsListContainer, message, Snackbar.LENGTH_LONG);
    }

    @Override
    public void onCardClick(final Article selectedArticle) {
        Snackbar cardClickSnackbar = createSnackBar(selectedArticle.getTitle());
        cardClickSnackbar.setAction("Go to website", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(selectedArticle.getUrl()));
                startActivity(i);
            }
        });
        cardClickSnackbar.show();
    }
}
