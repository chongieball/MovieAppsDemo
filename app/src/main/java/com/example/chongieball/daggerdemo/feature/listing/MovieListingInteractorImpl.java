package com.example.chongieball.daggerdemo.feature.listing;

import com.example.chongieball.daggerdemo.data.network.Service;
import com.example.chongieball.daggerdemo.data.network.response.movie.MovieResponse;
import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.feature.favorites.FavoriteInteractor;
import com.example.chongieball.daggerdemo.feature.listing.sorting.SortType;
import com.example.chongieball.daggerdemo.feature.listing.sorting.SortingStore;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by chongieball on 19/02/18.
 */

class MovieListingInteractorImpl implements MovieListingInteractor {

    private FavoriteInteractor favoriteInteractor;
    private Service webService;
    private SortingStore sortingStore;

    @Inject
    MovieListingInteractorImpl(FavoriteInteractor favoriteInteractor, Service
            webService, SortingStore sortingStore) {
        this.favoriteInteractor = favoriteInteractor;
        this.webService = webService;
        this.sortingStore = sortingStore;
    }

    @Override
    public Observable<List<MoviesWraper>> fetchMovies() {
        int selectedOption = sortingStore.getSelectedOption();

        if (selectedOption == SortType.MOST_POPULAR.getValue())
            return webService.popularMovies().map(MovieResponse::getMovies);
        else if (selectedOption == SortType.HIGHEST_RATED.getValue())
            return webService.highestRatedMovies().map
                    (MovieResponse::getMovies);
        else return Observable.just(favoriteInteractor.getFavorites());
    }
}
