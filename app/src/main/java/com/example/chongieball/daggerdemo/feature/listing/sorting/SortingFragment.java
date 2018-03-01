package com.example.chongieball.daggerdemo.feature.listing.sorting;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.chongieball.daggerdemo.R;
import com.example.chongieball.daggerdemo.feature.listing.MovieListingPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * Created by chongieball on 19/02/18.
 */

public class SortingFragment extends DialogFragment implements SortingView,
        RadioGroup.OnCheckedChangeListener {

    @Inject
    SortingPresenter sortingPresenter;

    @BindView(R.id.most_popular) RadioButton mostPopular;
    @BindView(R.id.highest_rated) RadioButton highestRated;
    @BindView(R.id.favorites) RadioButton favorites;
    @BindView(R.id.sorting_group) RadioGroup sortingOptionsGroup;

    private static MovieListingPresenter movieListingPresenter;
    private Unbinder unbinder;

    public static SortingFragment newInstance(MovieListingPresenter movieListingPresenter) {
        SortingFragment.movieListingPresenter = movieListingPresenter;
        return new SortingFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) unbinder.unbind();

        super.onDestroyView();
        sortingPresenter.destroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        sortingPresenter.setView(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.sorting_options, null);
        unbinder = ButterKnife.bind(this, dialogView);
        initViews();


        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(dialogView);
        dialog.setTitle("Sort By");
        dialog.show();
        return dialog;
    }

    private void initViews() {
        sortingPresenter.setLastSavedOption();
        sortingOptionsGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void setPopularChecked() {
        mostPopular.setChecked(true);
    }

    @Override
    public void setHighestRatedChecked() {
        highestRated.setChecked(true);
    }

    @Override
    public void setFavoritesChecked() {
        favorites.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.most_popular:
                sortingPresenter.onPopularMoviesSelected();
                movieListingPresenter.displayMovies();
                break;
            case R.id.highest_rated:
                sortingPresenter.onHighestRatedMoviesSelected();
                movieListingPresenter.displayMovies();
                break;
            case R.id.favorites:
                sortingPresenter.onFavoritesSelected();
                movieListingPresenter.displayMovies();
                break;
        }
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }
}
