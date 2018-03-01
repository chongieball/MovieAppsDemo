package com.example.chongieball.daggerdemo.feature.listing.sorting;

import javax.inject.Inject;

/**
 * Created by chongieball on 19/02/18.
 */

class SortingInteractorImpl implements SortingInteractor {

    private SortingStore store;

    @Inject
    public SortingInteractorImpl(SortingStore store) {
        this.store = store;
    }

    @Override
    public int getSelectedSortingOption() {
        return store.getSelectedOption();
    }

    @Override
    public void setSortingOption(SortType sortType) {
        store.setSelectedOption(sortType);
    }
}
