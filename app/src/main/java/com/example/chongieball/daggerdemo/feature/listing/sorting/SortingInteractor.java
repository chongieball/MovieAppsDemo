package com.example.chongieball.daggerdemo.feature.listing.sorting;

/**
 * Created by chongieball on 19/02/18.
 */

public interface SortingInteractor {

    int getSelectedSortingOption();

    void setSortingOption(SortType sortType);
}
