package com.example.chongieball.daggerdemo.feature.listing;

import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.feature.base.BaseView;

import java.util.List;

/**
 * Created by chongieball on 19/02/18.
 */

public interface MovieListingView extends BaseView {

    void showMovies(List<MoviesWraper> movies);

    void onMovieClicked(MoviesWraper movie);

    void loadingStarted();

    void loadingFailed(String errorMessage);
}
