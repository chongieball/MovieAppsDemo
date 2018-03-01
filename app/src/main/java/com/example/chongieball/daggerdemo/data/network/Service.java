package com.example.chongieball.daggerdemo.data.network;

import com.example.chongieball.daggerdemo.data.network.response.movie.MovieResponse;
import com.example.chongieball.daggerdemo.data.network.response.review.ReviewResponse;
import com.example.chongieball.daggerdemo.data.network.response.video.VideosResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by chongieball on 15/02/18.
 */

public interface Service {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Observable<MovieResponse> popularMovies();

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    Observable<MovieResponse> highestRatedMovies();

    @GET("3/movie/{movieId}/videos")
    Observable<VideosResponse> trailers(@Path("movieId") String movieId);

    @GET("3/movie/{movieId}/reviews")
    Observable<ReviewResponse> reviews(@Path("movieId") String movieId);
}
