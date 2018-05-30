package com.example.android.popularmovies2;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    // set up Movie object components
    private String mId;
    private String mPoster_path;
    private String mTitle;
    private String mRelease_date;
    private String mVote_average;
    private String mOverview;

    public Movie(String id, String poster_path, String title, String release_date, String vote_average, String overview) {
        mId = id;
        mPoster_path = poster_path;
        mTitle = title;
        mRelease_date = release_date;
        mVote_average = vote_average;
        mOverview = overview;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setPoster_path(String poster_path) {
        mPoster_path = poster_path;
    }

    public String getPoster_path() {
        return mPoster_path;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setRelease_date(String release_date) {
        mRelease_date = release_date;
    }

    public String getRelease_date() {
        return mRelease_date;
    }

    public void setVote_average(String vote_average) {
        mVote_average = vote_average;
    }

    public String getVote_average() {
        return mVote_average;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getOverview() {
        return mOverview;
    }

    // functions needed to make Movie Parcel
    // Parcelable pattern taken from http://www.vogella.com/tutorials/AndroidParcelable/article.html
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel fromParcel) {
            return new Movie(fromParcel);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(Parcel fromParcel) {
        this.mId = fromParcel.readString();
        this.mPoster_path = fromParcel.readString();
        this.mTitle = fromParcel.readString();
        this.mRelease_date = fromParcel.readString();
        this.mVote_average = fromParcel.readString();
        this.mOverview = fromParcel.readString();
    }

    @Override
    public void writeToParcel(Parcel toParcel, int flags) {
        toParcel.writeString(this.mId);
        toParcel.writeString(this.mPoster_path);
        toParcel.writeString(this.mTitle);
        toParcel.writeString(this.mRelease_date);
        toParcel.writeString(this.mVote_average);
        toParcel.writeString(this.mOverview);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + mId + '\'' +
                ",poster_path='" + mPoster_path + '\'' +
                ",title='" + mTitle + '\'' +
                ",release_date='" + mRelease_date + '\'' +
                ",vote_average='" + mVote_average + '\'' +
                ",overview='" + mOverview + '\'' +
                '}';
    }
}
