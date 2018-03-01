package com.example.chongieball.daggerdemo.feature.listing;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.chongieball.daggerdemo.R;
import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.feature.listing.sorting.SortingFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * Created by chongieball on 22/02/18.
 */

public class MovieListingFragment extends Fragment implements MovieListingView {
    private final String TAG = getClass().getSimpleName();

    @Inject
    MovieListingPresenter presenter;

    @BindView(R.id.movies_listing)
    RecyclerView moviesListing;

    private RecyclerView.Adapter adapter;
    private List<MoviesWraper> moviesWraperList = new ArrayList<>(20);
    private Callback callback;
    private Unbinder unbinder;

    public interface Callback
    {
        void onMoviesLoaded(MoviesWraper movie);

        void onMovieClicked(MoviesWraper movie);
    }

    public MovieListingFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        callback = (Callback) getActivity();
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container,
                false);
        unbinder = ButterKnife.bind(this, rootView);
        initLayoutReferences();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                displaySortingOptions();
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySortingOptions() {
        DialogFragment sortingDialogFragment = SortingFragment.newInstance
                (presenter);
        sortingDialogFragment.show(getFragmentManager(), "Select Quantity");
    }

    private void initLayoutReferences() {
        moviesListing.setHasFixedSize(true);

        int columns;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            columns = 2;
        else columns = getResources().getInteger(R.integer.no_of_columns);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager
                (getActivity(), columns);

        moviesListing.setLayoutManager(layoutManager);
        adapter = new MovieListingAdapter(moviesWraperList, this);
        moviesListing.setAdapter(adapter);
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Snackbar.make(moviesListing, errorMessage, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void loadingStarted() {
        Snackbar.make(moviesListing, "Loading Movies", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieClicked(MoviesWraper movie) {
        callback.onMovieClicked(movie);
    }

    @Override
    public void showMovies(List<MoviesWraper> movies) {
        this.moviesWraperList.clear();
        this.moviesWraperList.addAll(movies);
        moviesListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        callback.onMoviesLoaded(movies.get(0));
    }
}
