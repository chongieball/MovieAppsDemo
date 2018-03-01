package com.example.chongieball.daggerdemo.data.network.response.video;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.chongieball.daggerdemo.data.network.Api;
import com.example.chongieball.daggerdemo.utils.Constants;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chongieball on 15/02/18.
 */

public class VideosWraper implements Parcelable {
    public static final String SITE_YOUTUBE = "YouTube";

    private String id;
    private String name;
    private String site;
    private int size;
    private String type;

    @SerializedName("key")
    private String videoId;

    public VideosWraper() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getVideoId() {
        return videoId;
    }

    public static String getUrl(VideosWraper video) {
        if (SITE_YOUTUBE.equalsIgnoreCase(video.getSite())) {
            return String.format(Api.YOUTUBE_VIDEO_URL, video.getVideoId());
        } else {
            return Constants.EMPTY;
        }
    }

    public static String getThumbnailUrl(VideosWraper video) {
        if (SITE_YOUTUBE.equalsIgnoreCase(video.getSite())) {
            return String.format(Api.YOUTUBE_THUMBNAIL_URL, video.getVideoId());
        } else {
            return Constants.EMPTY;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.site);
        dest.writeInt(this.size);
        dest.writeString(this.type);
        dest.writeString(this.videoId);
    }

    protected VideosWraper(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.site = in.readString();
        this.size = in.readInt();
        this.type = in.readString();
        this.videoId = in.readString();
    }

    public static final Creator<VideosWraper> CREATOR = new Creator<VideosWraper>() {
        @Override
        public VideosWraper createFromParcel(Parcel source) {
            return new VideosWraper(source);
        }

        @Override
        public VideosWraper[] newArray(int size) {
            return new VideosWraper[size];
        }
    };
}
