package com.example.chongieball.daggerdemo.feature.listing;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.example.chongieball.daggerdemo.R;
import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.feature.base.BaseActivity;
import com.example.chongieball.daggerdemo.feature.details.MovieDetailsActivity;
import com.example.chongieball.daggerdemo.feature.details.MovieDetailsFragment;
import com.example.chongieball.daggerdemo.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Created by chongieball on 22/02/18.
 */

public class MovieListingActivity extends BaseActivity implements
        MovieListingFragment.Callback {
    private final String TAG = getClass().getSimpleName();

    public static final String DETAILS_FRAGMENT = "DetailsFragment";
    private boolean twoPaneMode;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();

        if (findViewById(R.id.movie_details_container) != null) {

            if (savedInstanceState != null) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container, new MovieDetailsFragment())
                        .commit();
            }
        } else twoPaneMode = false;
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("MovieGuide");
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onMoviesLoaded(MoviesWraper movie) {
        if (twoPaneMode) loadMovieFragment(movie);
    }

    @Override
    public void onMovieClicked(MoviesWraper movie) {
        if (twoPaneMode) loadMovieFragment(movie);
        else startMovieActivity(movie);
    }

    private void startMovieActivity(MoviesWraper moviesWraper) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.MOVIE, moviesWraper);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private void loadMovieFragment(MoviesWraper wraper) {
        MovieDetailsFragment fragment = MovieDetailsFragment.getInstance
                (wraper);
        toFragment(R.id.movie_details_container, fragment);
    }
}
