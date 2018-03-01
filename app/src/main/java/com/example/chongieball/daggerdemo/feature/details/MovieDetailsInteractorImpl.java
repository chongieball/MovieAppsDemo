package com.example.chongieball.daggerdemo.feature.details;

import com.example.chongieball.daggerdemo.data.network.Service;
import com.example.chongieball.daggerdemo.data.network.response.review.ReviewResponse;
import com.example.chongieball.daggerdemo.data.network.response.review.ReviewsWraper;
import com.example.chongieball.daggerdemo.data.network.response.video.VideosResponse;
import com.example.chongieball.daggerdemo.data.network.response.video.VideosWraper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by chongieball on 19/02/18.
 */

class MovieDetailsInteractorImpl implements MovieDetailsInteractor {

    private Service webService;

    @Inject
    public MovieDetailsInteractorImpl(Service webService) {
        this.webService = webService;
    }

    @Override
    public Observable<List<VideosWraper>> getTrailers(String id) {
        return webService.trailers(id).map(VideosResponse::getVideosWrapers);
    }

    @Override
    public Observable<List<ReviewsWraper>> getReviews(String id) {
        return webService.reviews(id).map(ReviewResponse::getReviews);
    }
}
