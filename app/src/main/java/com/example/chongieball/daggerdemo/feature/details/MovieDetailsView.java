package com.example.chongieball.daggerdemo.feature.details;



import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.data.network.response.review.ReviewsWraper;
import com.example.chongieball.daggerdemo.data.network.response.video.VideosWraper;
import com.example.chongieball.daggerdemo.feature.base.BaseView;

import java.util.List;

/**
 * Created by chongieball on 19/02/18.
 */

public interface MovieDetailsView extends BaseView {

    void showDetails(MoviesWraper moviesWraper);

    void showTrailers(List<VideosWraper> videosWrapers);

    void showReviews(List<ReviewsWraper> reviewsWrapers);

    void showFavorited();

    void showUnfavorited();
}
