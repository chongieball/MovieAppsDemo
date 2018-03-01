package com.example.chongieball.daggerdemo.feature.details;

import com.example.chongieball.daggerdemo.RxSchedulerRule;
import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.data.network.response.review.ReviewsWraper;
import com.example.chongieball.daggerdemo.data.network.response.video.VideosWraper;
import com.example.chongieball.daggerdemo.feature.favorites.FavoriteInteractor;
import com.example.chongieball.daggerdemo.feature.listing.MovieListingPresenterImpl;

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
public class MovieDetailsPresenterImplTest {

    @Rule
    public RxSchedulerRule rule = new RxSchedulerRule();

    @Mock
    private MovieDetailsInteractor movieDetailsInteractor;

    @Mock
    private FavoriteInteractor favoriteInteractor;

    @Mock
    private MovieDetailsView view;

    @Mock
    private List<VideosWraper> videosWrapers;

    @Mock
    private List<ReviewsWraper> reviewsWrapers;

    @Mock
    private MoviesWraper moviesWraper;

    private MovieDetailsPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MovieDetailsPresenterImpl(movieDetailsInteractor,
                favoriteInteractor);

        presenter.setView(view);
    }

    @After
    public void tearDown() throws Exception {
        presenter.destroyView();
    }

    @Test
    public void showDetails() {
        //when
        presenter.showDetails(moviesWraper);

        //then
        verify(view).showDetails(moviesWraper);
    }

    @Test
    public void showTrailers() {
        //given
        Observable<List<VideosWraper>> response = Observable.just(videosWrapers);
        when(movieDetailsInteractor.getTrailers(moviesWraper.getId())).thenReturn(response);

        //when
        presenter.showTrailers(moviesWraper);

        //then
        verify(view).showTrailers(videosWrapers);
    }

    @Test
    public void showReviews() {
        //given
        Observable<List<ReviewsWraper>> response = Observable.just(reviewsWrapers);
        when(movieDetailsInteractor.getReviews(moviesWraper.getId())).thenReturn(response);

        //when
        presenter.showReviews(moviesWraper);

        //then
        verify(view).showReviews(reviewsWrapers);
    }

    @Test
    public void showFavoriteButton() {
        //scenario
        //i want show favorite button when movie is not yet favorited

        //given
        when(favoriteInteractor.isFavorite(moviesWraper.getId())).thenReturn(true);

        //when
        presenter.showFavoriteButton(moviesWraper);

        //then
        verify(view).showFavorited();
    }

    @Test
    public void onFavoriteClick() {
        //scenario
        // i want favorite movie

        //given
        when(favoriteInteractor.isFavorite(moviesWraper.getId())).thenReturn(true);

        //when
        presenter.onFavoriteClick(moviesWraper);

        //then
        verify(view).showUnfavorited();
    }
}