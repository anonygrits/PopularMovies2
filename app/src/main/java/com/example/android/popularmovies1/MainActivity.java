package com.example.android.popularmovies1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import com.example.android.popularmovies1.Utilities.MovieJSONUtils;
import com.example.android.popularmovies1.Utilities.NetworkUtils;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieOnClickHandler {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private final String POPULAR_MOVIES_URL="https://api.themoviedb.org/3/movie/popular?";
    private final String TOP_RATED_MOVIES_URL="https://api.themoviedb.org/3/movie/top_rated?";

    private static String API_KEY_TAG="api_key";

    // put private API key here
    private static String API = "";      // todo remove before committing to github!

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get movies list (default ordering is by popularity)
        new FetchMovieListTask().execute(POPULAR_MOVIES_URL);

        // get reference to RecyclerView in xml
        mRecyclerView = findViewById(R.id.recyclerview);

        // changes in content don't change child layout size (performance)
        mRecyclerView.setHasFixedSize(true);

        // set up grid layout manager
        int numberColumns = 2;  // todo make adaptive to screen size
        mGridLayoutManager = new GridLayoutManager(this, numberColumns);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        // set up adapter to display each item in grid
        mAdapter = new MovieAdapter(mMovies, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("movie",new Movie(movie.getId(),movie.getPoster_path(),movie.getTitle(),movie.getRelease_year(),movie.getVote_average(),movie.getOverview()));
        startActivity(intentToStartDetailActivity);
    }

    public class FetchMovieListTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            if (urls.length == 0) return null;

            String movieListURL = urls[0];
            try {
                String moviesListJSON = NetworkUtils.getMovieList(movieListURL,API_KEY_TAG,API);
                return moviesListJSON;
            } catch (Exception e) {
                e.printStackTrace();
                return null;  // todo figure out what to do if this happens
            }
        }

        @Override
        protected void onPostExecute(String moviesListJSON) {
            try {
                ArrayList<Movie> moviesArrayList = MovieJSONUtils.getMovieArrayList(moviesListJSON);
                mMovies = moviesArrayList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_ordering, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.popular_movies) {
            new FetchMovieListTask().execute(POPULAR_MOVIES_URL);
            mAdapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.top_rated_movies) {
            new FetchMovieListTask().execute(TOP_RATED_MOVIES_URL);
            mAdapter.notifyDataSetChanged();
            return true;
        } else return super.onOptionsItemSelected(item);
    }

}
