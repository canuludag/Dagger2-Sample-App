package com.uludag.can.dagger2_sample_app.networking;

import com.uludag.can.dagger2_sample_app.model.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("everything")
    Call<ArticlesResponse> getArticles(@Query("sources") String sources);

    @GET("everything")
    Call<ArticlesResponse> searchArticles(@Query("q") String searchQueryString, @Query("sortBy") String sortBy);

}
