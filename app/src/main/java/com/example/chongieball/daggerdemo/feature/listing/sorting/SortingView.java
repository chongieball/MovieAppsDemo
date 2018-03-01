package com.example.chongieball.daggerdemo.feature.listing.sorting;


import com.example.chongieball.daggerdemo.feature.base.BaseView;

/**
 * Created by chongieball on 19/02/18.
 */

public interface SortingView extends BaseView {

    void setPopularChecked();

    void setHighestRatedChecked();

    void setFavoritesChecked();

    void dismissDialog();
}
