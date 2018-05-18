package com.example.android.popularmovies1.Utilities;

import android.content.ContentValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android.popularmovies1.Movie;

public final class MovieJSONUtils {
    public static void movieArray(String movieString) throws JSONException {
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
            Movie movie = new Movie(id,poster_path,title,release_date,vote_average,overview);

            // todo put movie object somewhere
        }
    }
}
