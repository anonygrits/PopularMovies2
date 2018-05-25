package com.example.android.popularmovies1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.android.popularmovies1.Utilities.NetworkUtils;

// separate file for asnycTask
// method taken from https://xelsoft.wordpress.com/2014/11/28/asynctask-implementation-using-callback-interface/
public class FetchMovieListTask extends AsyncTask<String, Void, String> {
    Context mContext;
    OnTaskCompleted mOnTaskCompleted;

    public FetchMovieListTask(Context context, OnTaskCompleted onTaskCompleted) {
        mContext = context;
        mOnTaskCompleted = onTaskCompleted;
    }


    // movieDB API key variables
    private static String API_KEY_TAG="api_key";
    private static String API = BuildConfig.API_KEY;

    // get data in background
    @Override
    protected String doInBackground(String... urls) {
        if (urls.length == 0) return null;

        // check connection
        Boolean isOnline = isOnline();

        if (isOnline) {
            String movieListURL = urls[0];
            try {
                String moviesListJSON = NetworkUtils.getMovieList(movieListURL, API_KEY_TAG, API);
                return moviesListJSON;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    // set data & recyclerview once data arrives
    @Override
    protected void onPostExecute(String moviesListJSON) {
        super.onPostExecute(moviesListJSON);
        if (moviesListJSON != null) {
            if (mOnTaskCompleted != null) {
                mOnTaskCompleted.onTaskCompleted(moviesListJSON);
            } else {
                Toast.makeText(mContext,"null object",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(mContext, R.string.no_data_error, Toast.LENGTH_LONG).show();
        }
    }

    // for checking network status
    // taken from https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
