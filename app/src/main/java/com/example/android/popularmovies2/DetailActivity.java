package com.example.android.popularmovies2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.example.android.popularmovies2.Utilities.NetworkUtils;

public class DetailActivity extends AppCompatActivity {
    // set up view variables
    private ImageView mPosterView;
    private TextView mDateView;
    private TextView mAverageVoteView;
    private TextView mOverviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get references to all views
        mPosterView = findViewById(R.id.iv_poster);
        mDateView = findViewById(R.id.tv_date);
        mAverageVoteView = findViewById(R.id.tv_avgVote);
        mOverviewView = findViewById(R.id.tv_overview);

        // get intent & check position
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("movie")) {
                Movie movie = intent.getExtras().getParcelable("movie");

                // set views
                mDateView.setText(movie.getRelease_date());
                mAverageVoteView.setText(movie.getVote_average());
                mOverviewView.setText(movie.getOverview());

                // get poster thumbnail using Picasso
                String posterURL = NetworkUtils.buildPosterURLString(movie.getPoster_path());
                Picasso.with(mPosterView.getContext()).load(posterURL).into(mPosterView);

                // set title
                setTitle(movie.getTitle());
            }
        }
    }
}
