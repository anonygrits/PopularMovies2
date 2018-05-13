package com.example.android.popularmovies1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public String getId() {
        return mId;
    }

    public String getPoster_path() {
        return mPoster_path;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getRelease_year() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDate = LocalDate.parse(mRelease_date, formatter);
        mRelease_year = Integer.toString(localDate.getYear());
        return mRelease_year;
    }

    public String getVote_average() {
        return mVote_average;
    }

    public String getOverview() {
        return mOverview;
    }
}
