package com.example.chongieball.daggerdemo.feature.details;

import com.example.chongieball.daggerdemo.data.network.response.review.ReviewsWraper;
import com.example.chongieball.daggerdemo.data.network.response.video.VideosWraper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chongieball on 19/02/18.
 */

public interface MovieDetailsInteractor {

    Observable<List<VideosWraper>> getTrailers(String id);

    Observable<List<ReviewsWraper>> getReviews(String id);
}
