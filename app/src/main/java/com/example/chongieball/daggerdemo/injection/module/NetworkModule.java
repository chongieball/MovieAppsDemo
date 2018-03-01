package com.example.chongieball.daggerdemo.injection.module;

import com.example.chongieball.daggerdemo.BuildConfig;
import com.example.chongieball.daggerdemo.data.network.RequestInterceptor;
import com.example.chongieball.daggerdemo.data.network.Service;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chongieball on 15/02/18.
 */

@Module
public class NetworkModule {
    public static final int CONNECT_TIMEOUT_IN_MS = 30000;

    @Provides
    @Singleton
    Interceptor requestInterceptor(RequestInterceptor interceptor) {
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(RequestInterceptor interceptor) {
        HttpLoggingInterceptor loggingInterceptor = new
                HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    Service getService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }
}
