package com.example.chongieball.daggerdemo.data.network.response.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chongieball on 15/02/18.
 */

public class MovieResponse {

    @SerializedName("results")
    private List<MoviesWraper> movies;

    public List<MoviesWraper> getMovies() {
        return movies;
    }

    public void setMovies(List<MoviesWraper> movies) {
        this.movies = movies;
    }
}
