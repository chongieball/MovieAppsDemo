package com.example.chongieball.daggerdemo.feature.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by chongieball on 19/02/18.
 */

@Singleton
public class FavoritesStore {

    private static final String PREF_NAME = "FavoritesStore";
    private SharedPreferences preferences;

    @Inject
    public FavoritesStore(Context context) {
        preferences = context.getApplicationContext().getSharedPreferences
                (PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setFavorite(MoviesWraper moviesWraper) {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String movieJson = gson.toJson(moviesWraper);

        editor.putString(moviesWraper.getId(), movieJson);
        editor.apply();
    }

    public boolean isFavorite(String id) {
        String movieJson = preferences.getString(id, null);

        return !TextUtils.isEmpty(movieJson);
    }

    public List<MoviesWraper> getFavorites() throws IOException {
        Map<String, ?> allEntries = preferences.getAll();
        ArrayList<MoviesWraper> moviesWrapers = new ArrayList<>(24);
        Gson gson = new Gson();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String movieJson = preferences.getString(entry.getKey(), null);

            if (!TextUtils.isEmpty(movieJson)) {
                MoviesWraper moviesWraper = gson.fromJson(movieJson,
                        MoviesWraper.class);
                moviesWrapers.add(moviesWraper);
            }
        }

        return moviesWrapers;
    }

    public void unfavorite(String id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(id);
        editor.apply();
    }
}
