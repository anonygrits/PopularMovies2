package com.example.android.popularmovies1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmovies1.Utilities.GridSizeUtils;
import com.example.android.popularmovies1.Utilities.MovieJSONUtils;
import com.example.android.popularmovies1.Utilities.NetworkUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieOnClickHandler {
    // set up variable to hold movie data
    private ArrayList<Movie> mMovies = new ArrayList<>();

    // set up variables for moviesDB
    private final String POPULAR_MOVIES_URL="https://api.themoviedb.org/3/movie/popular?";
    private final String TOP_RATED_MOVIES_URL="https://api.themoviedb.org/3/movie/top_rated?";

    private static String API_KEY_TAG="api_key";

    // todo put private API key here (remove before git commit!)
    private static String API = "";


    // set up components for recyclerview
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;

    // set up app when first opened
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get movies list (default ordering is by popularity)
        // note: in future versions, consider setting up local preferences to set default sort option
        // note: tried to put this in onPostExecute() but due to time lag, mMovie was set after recyclerview was set up
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

    // used to go from MainActivity to DetailActivity when user clicks on poster
    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("movie",new Movie(movie.getId(),movie.getPoster_path(),movie.getTitle(),movie.getRelease_date(),movie.getVote_average(),movie.getOverview()));
        startActivity(intentToStartDetailActivity);
    }

    // for checking network status
    // taken from https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    // get movie data asynchronously
    public class FetchMovieListTask extends AsyncTask<String, Void, String> {
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
    }

    // used to set the movie data
    // note: would normally do this with onPostExecute(), time lag results in blank screen till next action
    public void setmMovies(String url) {
        try {
            String moviesListJSON  = new  FetchMovieListTask().execute(url).get();

            // check for null result, which could mean that there is no network connection or no internet access
            if (moviesListJSON != null) {
                ArrayList<Movie> moviesArrayList = MovieJSONUtils.getMovieArrayList(moviesListJSON);
                mMovies.clear();
                mMovies.addAll(moviesArrayList);
            } else {
                Toast.makeText(this, R.string.no_data_error,Toast.LENGTH_LONG).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // sets up menu options for sort ordering
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_ordering, menu);
        return true;
    }

    // changes results shown on screen based on sort ordering choice
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
