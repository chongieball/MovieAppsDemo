package com.example.chongieball.daggerdemo.feature.listing;

import com.example.chongieball.daggerdemo.feature.base.Presenter;

/**
 * Created by chongieball on 19/02/18.
 */

public interface MovieListingPresenter extends Presenter<MovieListingView> {

    void displayMovies();
}
