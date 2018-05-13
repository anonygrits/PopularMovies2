package com.example.android.popularmovies1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final List<Movie> mMovies = new ArrayList<>();

    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // todo: initialize movies list


        // get reference to RecyclerView in xml
        mRecyclerView = findViewById(R.id.recyclerview);

        // set up grid layout manager
        int numberColumns = 2;  // move this somewhere later?
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberColumns);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        // changes in content don't change child layout size (performance)
        mRecyclerView.setHasFixedSize(true);

        // set up adapter to display each item in grid
        mAdapter = new RecyclerViewAdapter(mMovies);
        mRecyclerView.setAdapter(mAdapter);
    }

    // todo: to give options for sort order
    // todo setup menu xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    // todo: take action for sort order
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
