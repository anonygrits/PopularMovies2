package com.example.android.popularmovies1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Movie {
    private String mId;
    private String mPoster_path;
    private String mTitle;
    private String mRelease_year;
    private String mVote_average;
    private String mOverview;

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

    public Void setRelease_year(String release_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDate = LocalDate.parse(release_date, formatter);
        mRelease_year = Integer.toString(localDate.getYear());
    }

    public String getRelease_year() {
        return mRelease_year;
    }

    public Void setVote_average(String vote_average) {
        mVote_average = vote_average;
    }

    public String getVote_average() {
        return mVote_average;
    }

    public Void setOverview(String overview) {
        mOverview = overview;
    }

    public String getOverview() {
        return mOverview;
    }

}
