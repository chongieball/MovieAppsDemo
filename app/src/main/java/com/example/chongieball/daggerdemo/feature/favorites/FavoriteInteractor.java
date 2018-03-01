package com.example.chongieball.daggerdemo.feature.favorites;


import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;

import java.util.List;

/**
 * Created by chongieball on 19/02/18.
 */

public interface FavoriteInteractor {

    void setFavorite(MoviesWraper moviesWraper);

    boolean isFavorite(String id);

    List<MoviesWraper> getFavorites();

    void unfavorite(String id);
}
