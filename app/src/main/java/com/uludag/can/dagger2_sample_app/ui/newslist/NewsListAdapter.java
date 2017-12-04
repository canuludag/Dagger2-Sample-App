package com.uludag.can.dagger2_sample_app.ui.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uludag.can.dagger2_sample_app.R;
import com.uludag.can.dagger2_sample_app.model.Article;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {

    private List<Article> mArticles;

    public NewsListAdapter(List<Article> articles) {
        mArticles = articles;
    }

    @Override
    public NewsListAdapter.NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_list_news, parent, false);

        return new NewsListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.NewsListViewHolder holder, int position) {

        final Article newsArticle = mArticles.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        Glide.with(holder.cardImageView.getContext())
                .load(newsArticle.getUrlToImage())
                .apply(requestOptions)
                .into(holder.cardImageView);

        // Set the texts
        holder.cardTitleTextView.setText(newsArticle.getTitle());
        holder.cardTimeTextView.setText(newsArticle.getPublishedAt());
        holder.cardContentTextView.setText(newsArticle.getDescription());

    }

    @Override
    public int getItemCount() {
        if (mArticles != null && mArticles.size() > 0) {
            return mArticles.size();
        }
        return 0;
    }

    public class NewsListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.card_news_image)
        ImageView cardImageView;
        @BindView(R.id.card_news_title)
        TextView cardTitleTextView;
        @BindView(R.id.card_news_time)
        TextView cardTimeTextView;
        @BindView(R.id.card_news_content)
        TextView cardContentTextView;

        public NewsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
