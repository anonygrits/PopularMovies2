package com.example.android.popularmovies1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.popularmovies1.Utilities.GridSizeUtils;
import com.example.android.popularmovies1.Utilities.MovieJSONUtils;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieOnClickHandler {
    Context mContext = this;

    // set up variable to hold movie data
    private ArrayList<Movie> mMovies = new ArrayList<>();

    // set up variables for moviesDB
    private final String POPULAR_MOVIES_URL = "https://api.themoviedb.org/3/movie/popular?";
    private final String TOP_RATED_MOVIES_URL = "https://api.themoviedb.org/3/movie/top_rated?";

    // set up components for recyclerview
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;

    // set up app when first opened
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get movies list (default ordering is by popularity) and set up RecyclerView
        FetchMovieListTask fetchMovieListTask = getNewFetchMovieTask();
        fetchMovieListTask.execute(POPULAR_MOVIES_URL);

        // set up RecyclerView
        setUpRecyclerView();
    }

    // set up new AsyncTask
    public FetchMovieListTask getNewFetchMovieTask() {
        // first define onTaskCompleted to pass to AsnycTask
        final OnTaskCompleted onTaskCompleted = new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String moviesListJSON) {
                try {
                    // set Movies arrayList
                    ArrayList<Movie> moviesArrayList = MovieJSONUtils.getMovieArrayList(moviesListJSON);
                    mMovies.clear();
                    mMovies.addAll(moviesArrayList);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        // create new AsyncTask and return it
        FetchMovieListTask fetchMovieListTask = new FetchMovieListTask(MainActivity.this, onTaskCompleted);
        return fetchMovieListTask;
    }

    // set up recyclerView
    public void setUpRecyclerView() {
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

    // go from MainActivity to DetailActivity when user clicks on poster
    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("movie", new Movie(movie.getId(), movie.getPoster_path(), movie.getTitle(), movie.getRelease_date(), movie.getVote_average(), movie.getOverview()));
        startActivity(intentToStartDetailActivity);
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
        FetchMovieListTask fetchMovieListTask = getNewFetchMovieTask();

        switch (item.getItemId()) {
            case R.id.popular_movies:
                fetchMovieListTask.execute(POPULAR_MOVIES_URL);
                return true;
            case R.id.top_rated_movies:
                fetchMovieListTask.execute(TOP_RATED_MOVIES_URL);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

