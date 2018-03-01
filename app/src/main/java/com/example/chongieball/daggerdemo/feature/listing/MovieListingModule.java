package com.example.chongieball.daggerdemo.feature.listing;

import com.example.chongieball.daggerdemo.data.network.Service;
import com.example.chongieball.daggerdemo.feature.favorites.FavoriteInteractor;
import com.example.chongieball.daggerdemo.feature.listing.sorting.SortingStore;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chongieball on 25/02/18.
 */

@Module
public class MovieListingModule {

    @Provides
    MovieListingInteractor provideMovieListingInteractor(FavoriteInteractor favoriteInteractor,
                                                         Service service,
                                                         SortingStore
                                                                 sortingStore) {
        return new MovieListingInteractorImpl(favoriteInteractor, service,
                sortingStore);
    }

    @Provides
    MovieListingPresenter provideMovieListingPresenter(MovieListingInteractor
                                                               interactor) {
        return new MovieListingPresenterImpl(interactor);
    }
}
