package com.example.chongieball.daggerdemo.feature.details;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chongieball.daggerdemo.R;
import com.example.chongieball.daggerdemo.data.network.Api;
import com.example.chongieball.daggerdemo.data.network.response.movie.MoviesWraper;
import com.example.chongieball.daggerdemo.data.network.response.review.ReviewsWraper;
import com.example.chongieball.daggerdemo.data.network.response.video.VideosWraper;
import com.example.chongieball.daggerdemo.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * Created by chongieball on 21/02/18.
 */

public class MovieDetailsFragment extends Fragment implements
        MovieDetailsView, View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

    @BindView(R.id.movie_poster)
    ImageView poster;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.movie_name)
    TextView title;
    @BindView(R.id.movie_year)
    TextView releaseDate;
    @BindView(R.id.movie_rating)
    TextView rating;
    @BindView(R.id.movie_description)
    TextView overview;
    @BindView(R.id.trailers_label)
    TextView label;
    @BindView(R.id.trailers)
    LinearLayout trailers;
    @BindView(R.id.trailers_container)
    HorizontalScrollView horizontalScrollView;
    @BindView(R.id.reviews_label)
    TextView reviews;
    @BindView(R.id.reviews)
    LinearLayout reviewsContainer;
    @BindView(R.id.favorite)
    FloatingActionButton favorite;
    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;

    private MoviesWraper movies;
    private Unbinder unbinder;

    public MovieDetailsFragment() {
    }

    public static MovieDetailsFragment getInstance(@NonNull MoviesWraper moviesWraper)
    {
        Bundle args = new Bundle();
        args.putParcelable(Constants.MOVIE, moviesWraper);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setToolbar();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            MoviesWraper moviesWraper = (MoviesWraper) getArguments().get
                    (Constants.MOVIE);

            if (moviesWraper != null) {
                this.movies = moviesWraper;
                movieDetailsPresenter.setView(this);
                movieDetailsPresenter.showDetails(movies);
                movieDetailsPresenter.showDetails(movies);
            }
        }
    }

    private void setToolbar() {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor
                (getActivity(), R.color.colorPrimary));
        collapsingToolbar.setTitle(getString(R.string.movie_details));
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setTitleEnabled(true);

        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public void showDetails(MoviesWraper moviesWraper) {
        Glide.with(getActivity()).load(Api.getBackdropPath(moviesWraper.getBackdropPath
                ()))
                .into(poster);
        title.setText(moviesWraper.getTitle());
        releaseDate.setText(String.format(getString(R.string.release_date),
                moviesWraper.getReleaseDate()));
        rating.setText(String.format(getString(R.string.rating), String
                .valueOf(moviesWraper.getVoteAverage())));
        overview.setText(moviesWraper.getOverview());
        movieDetailsPresenter.showTrailers(moviesWraper);
        movieDetailsPresenter.showReviews(moviesWraper);
    }

    @Override
    public void showTrailers(List<VideosWraper> videosWrapers) {
        if (videosWrapers.isEmpty()) {
            label.setVisibility(View.GONE);
            this.trailers.setVisibility(View.GONE);
            horizontalScrollView.setVisibility(View.GONE);
        } else {
            label.setVisibility(View.VISIBLE);
            this.trailers.setVisibility(View.VISIBLE);
            horizontalScrollView.setVisibility(View.VISIBLE);

            this.trailers.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();
            RequestOptions options = new RequestOptions()
                    .placeholder(R.color.colorPrimary)
                    .centerCrop()
                    .override(150, 150);

            for (VideosWraper trailer : videosWrapers) {
                View thumbContainer = inflater.inflate(R.layout.video, this.trailers, false);
                ImageView thumbView = ButterKnife.findById(thumbContainer, R.id.video_thumb);
                thumbView.setTag(VideosWraper.getUrl(trailer));
                thumbView.requestLayout();
                thumbView.setOnClickListener(this);
                Glide.with(getActivity())
                        .load(VideosWraper.getThumbnailUrl(trailer))
                        .apply(options)
                        .into(thumbView);
                this.trailers.addView(thumbContainer);
            }
        }
    }

    @Override
    public void showReviews(List<ReviewsWraper> reviewsWrapers) {
        if (reviewsWrapers.isEmpty()) {
            this.reviews.setVisibility(View.GONE);
            reviewsContainer.setVisibility(View.GONE);
        } else {
            this.reviews.setVisibility(View.VISIBLE);
            reviewsContainer.setVisibility(View.VISIBLE);

            reviewsContainer.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();

            for (ReviewsWraper review : reviewsWrapers) {
                ViewGroup reviewContainer = (ViewGroup) inflater.inflate(R.layout.review, reviewsContainer, false);
                TextView reviewAuthor = ButterKnife.findById(reviewContainer, R.id.review_author);
                TextView reviewContent = ButterKnife.findById(reviewContainer, R.id.review_content);
                reviewAuthor.setText(review.getAuthor());
                reviewContent.setText(review.getContent());
                reviewContent.setOnClickListener(this);
                reviewsContainer.addView(reviewContainer);
            }
        }
    }

    @Override
    public void showFavorited() {
        favorite.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_favorite_white_24dp));
    }

    @Override
    public void showUnfavorited() {
        favorite.setImageDrawable(ContextCompat.getDrawable(getActivity(), R
                .drawable.ic_favorite_white_border_24dp));
    }

    @OnClick(R.id.favorite)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_thumb:
                onThumbnailClick(view);
                break;

            case R.id.review_content:
                onReviewClick((TextView) view);
                break;

            case R.id.favorite:
                onFavoriteClick();
                break;

            default:
                break;
        }
    }

    private void onReviewClick(TextView view) {
        if (view.getMaxLines() == 5) view.setMaxLines(500);
        else view.setMaxLines(5);
    }

    private void onThumbnailClick(View view) {
        String videoUrl = (String) view.getTag();
        Intent playVideoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(playVideoIntent);
    }

    private void onFavoriteClick() {
        movieDetailsPresenter.onFavoriteClick(movies);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        movieDetailsPresenter.destroyView();
        unbinder.unbind();
    }
}
