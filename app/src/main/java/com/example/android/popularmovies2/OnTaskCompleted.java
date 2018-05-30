package com.example.android.popularmovies2;

public interface OnTaskCompleted {
    // taken from https://xelsoft.wordpress.com/2014/11/28/asynctask-implementation-using-callback-interface/
    void onTaskCompleted(String moviesListJSON);
}
