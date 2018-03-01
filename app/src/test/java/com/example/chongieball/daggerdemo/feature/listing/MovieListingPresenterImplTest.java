package com.example.chongieball.daggerdemo.feature.listing;

import com.example.chongieball.daggerdemo.RxSchedulerRule;
import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by chongieball on 01/03/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class MovieListingPresenterImplTest {

    @Rule
    public RxSchedulerRule rule = new RxSchedulerRule();

    @Mock
    private MovieListingInteractor interactor;

    @Mock
    private MovieListingView view;

    @Mock
    private List<MoviesWraper> movies;

    private MovieListingPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MovieListingPresenterImpl(interactor);
    }

    @After
    public void tearDown() {
        presenter.destroyView();
    }

    @Test
    public void showMovies() {
        //given
        Observable<List<MoviesWraper>> response = Observable.just(movies);
        when(interactor.fetchMovies()).thenReturn(response);

        //when:
        presenter.setView(view);

        //then:
        verify(view).showMovies(movies);
    }
}