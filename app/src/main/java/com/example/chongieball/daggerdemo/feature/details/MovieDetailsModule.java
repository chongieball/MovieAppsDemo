package com.example.chongieball.daggerdemo.feature.details;

import com.example.chongieball.daggerdemo.data.network.Service;
import com.example.chongieball.daggerdemo.feature.favorites.FavoriteInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chongieball on 25/02/18.
 */

@Module
public class MovieDetailsModule {

    @Provides
    MovieDetailsInteractor provideInteractor(Service service) {
        return new MovieDetailsInteractorImpl(service);
    }

    @Provides
    MovieDetailsPresenter providePresenter(MovieDetailsInteractor
                                                   detailsInteractor,
                                           FavoriteInteractor
                                                   favoriteInteractor) {
        return new MovieDetailsPresenterImpl(detailsInteractor,
                favoriteInteractor);
    }
}
