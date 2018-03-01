package com.example.chongieball.daggerdemo.feature.listing;


import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chongieball on 19/02/18.
 */

public interface MovieListingInteractor {

    Observable<List<MoviesWraper>> fetchMovies();
}
