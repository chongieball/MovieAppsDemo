package com.example.chongieball.daggerdemo.feature.listing.sorting;


import com.example.chongieball.daggerdemo.feature.base.Presenter;

/**
 * Created by chongieball on 19/02/18.
 */

public interface SortingPresenter extends Presenter<SortingView> {

    void setLastSavedOption();

    void onPopularMoviesSelected();

    void onHighestRatedMoviesSelected();

    void onFavoritesSelected();
}
