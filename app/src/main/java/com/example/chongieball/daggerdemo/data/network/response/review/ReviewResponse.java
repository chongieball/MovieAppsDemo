package com.example.chongieball.daggerdemo.data.network.response.review;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chongieball on 15/02/18.
 */

public class ReviewResponse {

    @SerializedName("results")
    private List<ReviewsWraper> reviews;

    public List<ReviewsWraper> getReviews() {
        return reviews;
    }
}
