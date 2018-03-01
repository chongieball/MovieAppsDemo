package com.example.chongieball.daggerdemo.feature.listing.sorting;

/**
 * Created by chongieball on 19/02/18.
 */

public enum SortType {

    MOST_POPULAR(0), HIGHEST_RATED(1), FAVORITES(2);

    private final int value;

    SortType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
