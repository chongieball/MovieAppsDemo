package com.example.chongieball.daggerdemo.feature.listing.sorting;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by chongieball on 19/02/18.
 */

public class SortingStore {

    private SharedPreferences preferences;
    private static final String SELECTED_OPTION = "selectedOption";
    private static final String PREF_NAME = "SortingStore";

    @Inject
    public SortingStore(Context context) {
        preferences = context.getApplicationContext().getSharedPreferences
                (PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setSelectedOption(SortType sortType) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(SELECTED_OPTION, sortType.getValue());
        editor.apply();
    }

    public int getSelectedOption() {
        return preferences.getInt(SELECTED_OPTION, 0);
    }
}
