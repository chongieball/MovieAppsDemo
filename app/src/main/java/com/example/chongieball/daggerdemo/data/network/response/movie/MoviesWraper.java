package com.example.chongieball.daggerdemo.data.network.response.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chongieball on 15/02/18.
 */

public class MoviesWraper implements Parcelable {

    private String id;
    private String overview;
    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("vote_average")
    private double voteAverage;

    public MoviesWraper() {}

    public String getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.overview);
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeDouble(this.voteAverage);
    }

    protected MoviesWraper(Parcel in) {
        this.id = in.readString();
        this.overview = in.readString();
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.voteAverage = in.readDouble();
    }

    public static final Creator<MoviesWraper> CREATOR = new Creator<MoviesWraper>() {
        @Override
        public MoviesWraper createFromParcel(Parcel source) {
            return new MoviesWraper(source);
        }

        @Override
        public MoviesWraper[] newArray(int size) {
            return new MoviesWraper[size];
        }
    };
}
