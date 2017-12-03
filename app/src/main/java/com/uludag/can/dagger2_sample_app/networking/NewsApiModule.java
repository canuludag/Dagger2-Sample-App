package com.uludag.can.dagger2_sample_app.networking;

import com.uludag.can.dagger2_sample_app.BuildConfig;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NewsApiModule {

    @Provides
    public OkHttpClient provideClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("X-Api-Key", BuildConfig.API_KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build();

    }

    @Provides
    public Retrofit provideRetrofit(String baseUrl, OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    public NewsApiService provideNewsApiService() {
        NewsApiService newsApiService = provideRetrofit(BuildConfig.BASE_URL, provideClient())
                .create(NewsApiService.class);

        return newsApiService;
    }

}
