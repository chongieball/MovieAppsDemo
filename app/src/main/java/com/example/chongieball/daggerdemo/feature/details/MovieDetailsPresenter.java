package com.example.chongieball.daggerdemo.feature.details;

import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.feature.base.Presenter;

/**
 * Created by chongieball on 21/02/18.
 */

public interface MovieDetailsPresenter extends Presenter<MovieDetailsView> {

    void showDetails(MoviesWraper moviesWraper);

    void showTrailers(MoviesWraper moviesWraper);

    void showReviews(MoviesWraper moviesWraper);

    void showFavoriteButton(MoviesWraper moviesWraper);

    void onFavoriteClick(MoviesWraper moviesWraper);
}
