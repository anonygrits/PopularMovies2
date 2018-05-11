package com.example.android.popularmovies1.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class MovieJSONUtils {
    public static JSONArray movieArray(String movieString) throws JSONException {
        JSONObject movieJSON = new JSONObject(movieString);
        JSONArray movieArray = new JSONArray(movieJSON.getJSONArray("results"));

        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movieData = movieArray.getJSONObject(i);

            String id = movieData.optString("id");
            String poster_path = movieData.optString("poster_path");
            String title = movieData.optString("title");
            String release_date = movieData.optString("release_date");
            // todo turn release_date into release_year
            String release_year = release_date;
            String vote_average = movieData.optString("vote_average");
            String overview = movieData.optString("overview");
        }
    }
}
