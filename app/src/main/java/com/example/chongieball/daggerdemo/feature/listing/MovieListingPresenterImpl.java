package com.example.chongieball.daggerdemo.feature.listing;

import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.feature.base.BasePresenter;
import com.example.chongieball.daggerdemo.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chongieball on 19/02/18.
 */

public class MovieListingPresenterImpl extends BasePresenter<MovieListingView> implements
        MovieListingPresenter {

    private MovieListingInteractor movieListingInteractor;
    private Disposable fetchSubscription;

    @Inject
    public MovieListingPresenterImpl(MovieListingInteractor movieListingInteractor) {
        this.movieListingInteractor = movieListingInteractor;
    }

    @Override
    public void setView(MovieListingView view) {
        super.setView(view);
        displayMovies();
    }

    @Override
    public void destroyView() {
        super.destroyView();
        RxUtils.unsubscribe(fetchSubscription);
    }

    @Override
    public void displayMovies() {
        getView().loadingStarted();

        fetchSubscription = movieListingInteractor.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieFetchSuccess, this::onMovieFetchFailed);
    }

    private void onMovieFetchSuccess(List<MoviesWraper> movieResponses) {
        getView().showMovies(movieResponses);
    }

    private void onMovieFetchFailed(Throwable e) {
        getView().loadingFailed(e.getMessage());
    }
}
