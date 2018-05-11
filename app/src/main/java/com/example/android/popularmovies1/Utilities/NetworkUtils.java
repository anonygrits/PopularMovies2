package com.example.android.popularmovies1.Utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {
    private final String POPULAR_MOVIES_URL="https://api.themoviedb.org/3/movie/popular?";
    private final String TOP_MOVIES_URL="https://api.themoviedb.org/3/movie/top_rated?";

    private static String API_KEY="api_key";


    // get movie list from API (adapted from Sunshine S02.01 Solution)
    public static String getMovieList(String movie_url, String api_key, String api) throws IOException {
        // build url inside this function but think about how to make a generic one to combine with thumbnail url builder
        Uri thumbnailURI = Uri.parse(movie_url).buildUpon()
                .appendQueryParameter(api_key,api)
                .build();
        URL url = null;
        try {
            url = new URL(thumbnailURI.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // make http connection and get data
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    // build thumbnail URL
    private static final String THUMBNAIL_BASE_URL="http://image.tmdb.org/t/p/";
    private static final String THUMBNAIL_SIZE="w185";
    final static String MOVIE_ID="movie_id";

    public static URL buildThumbnailURL(String movie_id) {
        Uri thumbnailURI = Uri.parse(THUMBNAIL_BASE_URL).buildUpon()
                .appendPath(THUMBNAIL_SIZE)
                .appendQueryParameter(MOVIE_ID,movie_id)
                .build();
        URL url = null;
        try {
            url = new URL(thumbnailURI.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
