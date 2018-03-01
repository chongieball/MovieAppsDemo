package com.example.chongieball.daggerdemo.feature.listing.sorting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by chongieball on 01/03/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class SortingPresenterImplTest {

    @Mock
    private SortingInteractor interactor;

    @Mock
    private SortingView view;

    private SortingPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SortingPresenterImpl(interactor);
        presenter.setView(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.destroyView();
    }

    @Test
    public void setLastSavedOption() {
        //set Sorting option
        interactor.setSortingOption(SortType.MOST_POPULAR);

        //given
        int selectedOption = interactor.getSelectedSortingOption();
        when(interactor.getSelectedSortingOption()).thenReturn(selectedOption);

        //when
        presenter.setLastSavedOption();

        //then
        verify(view).setPopularChecked();
    }

    @Test
    public void onPopularMovieSelected() {
        //given
        interactor.setSortingOption(SortType.MOST_POPULAR);

        //when
        presenter.onPopularMoviesSelected();

        //then
        verify(view).dismissDialog();
    }

    @Test
    public void onHighestRatedMoviesSelected() {
        //given
        interactor.setSortingOption(SortType.HIGHEST_RATED);

        //when
        presenter.onHighestRatedMoviesSelected();

        //then
        verify(view).dismissDialog();
    }

    @Test
    public void onFavoritesSelected() {
        //given
        interactor.setSortingOption(SortType.FAVORITES);

        //when
        presenter.onFavoritesSelected();

        //then
        verify(view).dismissDialog();
    }
}