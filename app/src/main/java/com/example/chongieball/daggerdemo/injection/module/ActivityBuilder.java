package com.example.chongieball.daggerdemo.injection.module;

import com.example.chongieball.daggerdemo.feature.details.MovieDetailsActivity;
import com.example.chongieball.daggerdemo.feature.details.MovieDetailsFragment;
import com.example.chongieball.daggerdemo.feature.details.MovieDetailsModule;
import com.example.chongieball.daggerdemo.feature.listing.MovieListingActivity;
import com.example.chongieball.daggerdemo.feature.listing.MovieListingFragment;
import com.example.chongieball.daggerdemo.feature.listing.MovieListingModule;
import com.example.chongieball.daggerdemo.feature.listing.sorting.SortingFragment;
import com.example.chongieball.daggerdemo.feature.listing.sorting.SortingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by chongieball on 28/02/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MovieListingModule
            .class})
    abstract MovieListingFragment bindListingFragment();

    @ContributesAndroidInjector(modules = {MovieDetailsModule.class})
    abstract MovieDetailsFragment bindDetailsFragment();

    @ContributesAndroidInjector(modules = {SortingModule.class})
    abstract SortingFragment bindSortingFragment();
}
