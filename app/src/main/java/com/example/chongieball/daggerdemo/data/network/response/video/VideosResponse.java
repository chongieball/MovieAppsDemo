package com.example.chongieball.daggerdemo.data.network.response.video;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chongieball on 15/02/18.
 */

public class VideosResponse {

    @SerializedName("results")
    private List<VideosWraper> videosWrapers;

    public List<VideosWraper> getVideosWrapers() {
        return videosWrapers;
    }

    public void setVideosWrapers(List<VideosWraper> videosWrapers) {
        this.videosWrapers = videosWrapers;
    }
}
