package com.example.android.popularmovies1;

public class Movie {
    private String mId;
    private String mPoster_path;
    private String mTitle;
    private String mRelease_date;
    private String mRelease_year;
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

    public String getmId() {
        return mId;
    }

    public String getmPoster_path() {
        return mPoster_path;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmRelease_year() {
        // todo get year from date
        return mRelease_date;
    }

    public String getmVote_average() {
        return mVote_average;
    }

    public String getmOverview() {
        return mOverview;
    }
}
