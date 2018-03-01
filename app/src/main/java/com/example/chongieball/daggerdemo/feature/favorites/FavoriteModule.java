package com.example.chongieball.daggerdemo.feature.favorites;

import com.example.chongieball.daggerdemo.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chongieball on 25/02/18.
 */

@Module
public class FavoriteModule {

    @Provides
    FavoriteInteractor provideFavoriteInteractor(FavoritesStore
                                                         favoritesStore) {
        return new FavoriteInteractorImpl(favoritesStore);
    }
}
