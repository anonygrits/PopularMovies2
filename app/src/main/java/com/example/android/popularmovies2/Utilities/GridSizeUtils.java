package com.example.android.popularmovies2.Utilities;

import android.content.Context;
import android.util.DisplayMetrics;

// code taken from https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns/33607316#33607316

public class GridSizeUtils {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 140);
        return noOfColumns;
    }
}
