package com.example.chongieball.daggerdemo.feature.details;

import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.data.network.response.review.ReviewsWraper;
import com.example.chongieball.daggerdemo.data.network.response.video.VideosWraper;
import com.example.chongieball.daggerdemo.feature.base.BasePresenter;
import com.example.chongieball.daggerdemo.feature.favorites.FavoriteInteractor;
import com.example.chongieball.daggerdemo.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chongieball on 21/02/18.
 */

class MovieDetailsPresenterImpl extends BasePresenter<MovieDetailsView>
        implements
        MovieDetailsPresenter {

    private MovieDetailsInteractor movieDetailsInteractor;
    private FavoriteInteractor favoriteInteractor;
    private Disposable trailerSubscription;
    private Disposable reviewSubscription;

    @Inject
    public MovieDetailsPresenterImpl(MovieDetailsInteractor movieDetailsInteractor, FavoriteInteractor favoriteInteractor) {
        this.movieDetailsInteractor = movieDetailsInteractor;
        this.favoriteInteractor = favoriteInteractor;
    }

    @Override
    public void destroyView() {
        super.destroyView();
        RxUtils.unsubscribe(trailerSubscription, reviewSubscription);
    }

    @Override
    public void showDetails(MoviesWraper moviesWraper) {
        getView().showDetails(moviesWraper);
    }

    @Override
    public void showTrailers(MoviesWraper moviesWraper) {
        trailerSubscription = movieDetailsInteractor.getTrailers(moviesWraper
                .getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTrailersSuccess, t -> onGetTrailersFailure());
    }

    private void onGetTrailersSuccess(List<VideosWraper> videos) {
        getView().showTrailers(videos);
    }

    private void onGetTrailersFailure() {
        // Do nothing
    }

    @Override
    public void showReviews(MoviesWraper moviesWraper) {
        reviewSubscription = movieDetailsInteractor.getReviews(moviesWraper.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetReviewsSuccess, t -> onGetTrailersFailure());
    }

    private void onGetReviewsSuccess(List<ReviewsWraper> reviews) {
        getView().showReviews(reviews);
    }

    private void onGetReviewsFailure() {
        // Do nothing
    }

    @Override
    public void showFavoriteButton(MoviesWraper moviesWraper) {
        boolean isFavorite = favoriteInteractor.isFavorite(moviesWraper.getId
                ());
        if (isFavorite) getView().showFavorited();
        else getView().showUnfavorited();
    }

    @Override
    public void onFavoriteClick(MoviesWraper moviesWraper) {
        boolean isFavorite = favoriteInteractor.isFavorite(moviesWraper.getId
                ());
        if (isFavorite) {
            favoriteInteractor.unfavorite(moviesWraper.getId());
            getView().showUnfavorited();
        } else {
            favoriteInteractor.setFavorite(moviesWraper);
            getView().showFavorited();
        }
    }
}
