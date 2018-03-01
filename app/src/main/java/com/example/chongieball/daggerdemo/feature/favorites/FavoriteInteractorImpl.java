package com.example.chongieball.daggerdemo.feature.favorites;


import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by chongieball on 19/02/18.
 */

class FavoriteInteractorImpl implements FavoriteInteractor {

    private FavoritesStore store;

    @Inject
    FavoriteInteractorImpl(FavoritesStore store) {
        this.store = store;
    }

    @Override
    public void setFavorite(MoviesWraper moviesWraper) {
        store.setFavorite(moviesWraper);
    }

    @Override
    public boolean isFavorite(String id) {
        return store.isFavorite(id);
    }

    @Override
    public List<MoviesWraper> getFavorites() {
        try {
            return store.getFavorites();
        } catch (IOException ignored) {
            return new ArrayList<>(0);
        }
    }

    @Override
    public void unfavorite(String id) {
        store.unfavorite(id);
    }
}
