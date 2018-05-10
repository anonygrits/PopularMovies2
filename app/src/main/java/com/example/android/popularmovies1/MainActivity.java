package com.example.android.popularmovies1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnClickListener {
    private static final int NUM_LIST_ITEMS=10;

    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference to RecyclerView in xml
        mRecyclerView = findViewById(R.id.recyclerview);

        // set up grid layout manager
        int numberColumns = 2;  // move this somewhere later?
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberColumns);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        // changes in content don't change child layout size (performance)
        mRecyclerView.setHasFixedSize(true);

        // set up adapter to display each item in grid
        mAdapter = new RecyclerViewAdapter(NUM_LIST_ITEMS,this);
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

    // todo figure out how to make click on thumbnail go to movie details (intent?)
    @Override
    public void onClick() {

    }
}
