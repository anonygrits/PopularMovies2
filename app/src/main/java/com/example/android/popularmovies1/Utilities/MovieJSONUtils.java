package com.example.android.popularmovies1.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            // todo turn release_date into release_year
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate localDate = LocalDate.parse(release_date, formatter);
            String release_year = Integer.toString(localDate.getYear());
            String vote_average = movieData.optString("vote_average");
            String overview = movieData.optString("overview");

            // todo put into some kind of object
        }

        // todo figure out what object to return
    }
}
