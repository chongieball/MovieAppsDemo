package com.example.chongieball.daggerdemo.data.network;

import android.util.Log;

import com.example.chongieball.daggerdemo.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chongieball on 15/02/18.
 */

@Singleton
public class RequestInterceptor implements Interceptor{

    @Inject
    public RequestInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl httpUrl = original.url();

        HttpUrl url = httpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build();

        Request request = original.newBuilder().url(url).build();

        return chain.proceed(request);
    }
}
