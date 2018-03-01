package com.example.chongieball.daggerdemo.data.network.response.review;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chongieball on 15/02/18.
 */

public class ReviewsWraper implements Parcelable {

    private String id;
    private String author;
    private String content;
    private String url;

    public ReviewsWraper() {}

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.author);
        dest.writeString(this.content);
        dest.writeString(this.url);
    }

    protected ReviewsWraper(Parcel in) {
        this.id = in.readString();
        this.author = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Creator<ReviewsWraper> CREATOR = new Creator<ReviewsWraper>() {
        @Override
        public ReviewsWraper createFromParcel(Parcel source) {
            return new ReviewsWraper(source);
        }

        @Override
        public ReviewsWraper[] newArray(int size) {
            return new ReviewsWraper[size];
        }
    };
}
