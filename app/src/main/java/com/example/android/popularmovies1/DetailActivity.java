package com.example.android.popularmovies1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        // todo: get movies list

        // todo: populate views

        // todo: setTitle(movie.getTitle())
    }

    // show error toast if movie is missing
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.missing_movie_message, Toast.LENGTH_SHORT).show();
    }
}
