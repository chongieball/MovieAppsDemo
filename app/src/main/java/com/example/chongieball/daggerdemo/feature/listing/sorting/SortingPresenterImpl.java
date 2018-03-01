package com.example.chongieball.daggerdemo.feature.listing.sorting;

import com.example.chongieball.daggerdemo.feature.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by chongieball on 19/02/18.
 */

class SortingPresenterImpl extends BasePresenter<SortingView> implements
        SortingPresenter {

    SortingInteractor sortingInteractor;

    public SortingPresenterImpl(SortingInteractor interactor) {
        this.sortingInteractor = interactor;
    }

    @Override
    public void setLastSavedOption() {
        int selectedOption = sortingInteractor.getSelectedSortingOption();

        if (selectedOption == SortType.MOST_POPULAR.getValue())
            getView().setPopularChecked();
        else if (selectedOption == SortType.HIGHEST_RATED.getValue())
            getView().setHighestRatedChecked();
        else getView().setFavoritesChecked();
    }

    @Override
    public void onPopularMoviesSelected() {
        sortingInteractor.setSortingOption(SortType.MOST_POPULAR);
        getView().dismissDialog();
    }

    @Override
    public void onHighestRatedMoviesSelected() {
        sortingInteractor.setSortingOption(SortType.HIGHEST_RATED);
        getView().dismissDialog();
    }

    @Override
    public void onFavoritesSelected() {
        sortingInteractor.setSortingOption(SortType.FAVORITES);
        getView().dismissDialog();
    }
}
