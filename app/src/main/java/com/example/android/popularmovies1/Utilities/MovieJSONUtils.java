package com.example.android.popularmovies1.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android.popularmovies1.Movie;

import java.util.ArrayList;

public final class MovieJSONUtils {
    public static ArrayList getMovieArrayList(String movieString) throws JSONException {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        JSONObject movieJSON = new JSONObject(movieString);
        JSONArray movieArray = new JSONArray(movieJSON.getJSONArray("results"));

        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movieData = movieArray.getJSONObject(i);

            String id = movieData.optString("id");
            String poster_path = movieData.optString("poster_path");
            String title = movieData.optString("title");
            String release_date = movieData.optString("release_date");
            String vote_average = movieData.optString("vote_average");
            String overview = movieData.optString("overview");

            // make movie object
            Movie movie = new Movie();

            movie.setId(id);
            movie.setPoster_path(poster_path);
            movie.setTitle(title);
            movie.setRelease_year(release_date);
            movie.setVote_average(vote_average);
            movie.setOverview(overview);

            // add movie to arrayList
            movieArrayList.add(movie);
        }

        return movieArrayList;
    }
}
