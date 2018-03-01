package com.example.chongieball.daggerdemo.feature.details;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.example.chongieball.daggerdemo.R;
import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.feature.base.BaseActivity;
import com.example.chongieball.daggerdemo.utils.Constants;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Created by chongieball on 21/02/18.
 */

public class MovieDetailsActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras != null && extras.containsKey(Constants.MOVIE)) {
                MoviesWraper moviesWraper = extras.getParcelable(Constants
                        .MOVIE);

                if (moviesWraper != null) {
                    MovieDetailsFragment fragment = MovieDetailsFragment
                            .getInstance(moviesWraper);
                    toFragment(R.id.movie_details_container, fragment);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
