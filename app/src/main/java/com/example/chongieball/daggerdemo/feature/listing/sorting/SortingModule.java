package com.example.chongieball.daggerdemo.feature.listing.sorting;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chongieball on 25/02/18.
 */

@Module
public class SortingModule {

    @Provides
    SortingInteractor provideSortingInteractor(SortingStore sortingStore) {
        return new SortingInteractorImpl(sortingStore);
    }

    @Provides
    SortingPresenter provideSortingPresenter(SortingInteractor
                                                     sortingInteractor) {
        return new SortingPresenterImpl(sortingInteractor);
    }
}
