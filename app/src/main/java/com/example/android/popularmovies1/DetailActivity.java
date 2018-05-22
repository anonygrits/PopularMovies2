package com.example.android.popularmovies1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.example.android.popularmovies1.Utilities.NetworkUtils;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView mPosterView;
    private TextView mYearView;
    private TextView mAverageVoteView;
    private TextView mOverviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mPosterView = findViewById(R.id.iv_poster);
        mYearView = findViewById(R.id.tv_year);
        mAverageVoteView = findViewById(R.id.tv_avgVote);
        mOverviewView = findViewById(R.id.tv_overview);

        // get intent & check position
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("movie")) {
                Movie movie = intent.getExtras().getParcelable("movie");

                // set views
                mYearView.setText(movie.getRelease_year());
                mAverageVoteView.setText(movie.getRelease_year());
                mAverageVoteView.setText(movie.getVote_average());
                mOverviewView.setText(movie.getOverview());

                // todo also in MovieAdapter - figure out where to put this in final code
                String posterURL = NetworkUtils.buildPosterURLString(movie.getPoster_path());
                Picasso.with(mPosterView.getContext()).load(posterURL).into(mPosterView);

                // set title
                setTitle(movie.getTitle());
            }
        }
    }
}
