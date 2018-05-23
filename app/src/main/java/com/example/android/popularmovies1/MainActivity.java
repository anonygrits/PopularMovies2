package com.example.android.popularmovies1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;

import com.example.android.popularmovies1.Utilities.GridSizeUtils;
import com.example.android.popularmovies1.Utilities.MovieJSONUtils;
import com.example.android.popularmovies1.Utilities.NetworkUtils;

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
        // note: tried to put this in onPostExecute() but could not set mMovie - revisit when time
        setmMovies(POPULAR_MOVIES_URL);

        // get reference to RecyclerView in xml
        mRecyclerView = findViewById(R.id.recyclerview);

        // changes in content don't change child layout size (performance)
        mRecyclerView.setHasFixedSize(true);

        // set up grid layout manager
        int numberColumns = GridSizeUtils.calculateNoOfColumns(getApplicationContext());
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
        intentToStartDetailActivity.putExtra("movie",new Movie(movie.getId(),movie.getPoster_path(),movie.getTitle(),movie.getRelease_date(),movie.getVote_average(),movie.getOverview()));
        startActivity(intentToStartDetailActivity);
    }

    public class FetchMovieListTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            if (urls.length == 0) return null;

            String movieListURL = urls[0];
            try {
                String moviesListJSON = NetworkUtils.getMovieList(movieListURL, API_KEY_TAG, API);
                return moviesListJSON;
            } catch (Exception e) {
                e.printStackTrace();
                return null;  // todo alert user via toast message?
            }
        }
    }

    public void setmMovies(String url) {
        try {
            String moviesListJSON  = new  FetchMovieListTask().execute(url).get();
            ArrayList<Movie> moviesArrayList = MovieJSONUtils.getMovieArrayList(moviesListJSON);
            mMovies.clear();
            mMovies.addAll(moviesArrayList);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_ordering, menu);
        return true;
    }

    // todo figure out how to save settings
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular_movies:
                setmMovies(POPULAR_MOVIES_URL);
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.top_rated_movies:
                setmMovies(TOP_RATED_MOVIES_URL);
                mAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
