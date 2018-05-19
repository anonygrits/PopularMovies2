package com.example.android.popularmovies1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.android.popularmovies1.Utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Movie> mMovies;

    // constructor for adapter
    public MyAdapter(ArrayList movies) {
        mMovies = movies;
    }

    // set up viewholder for each grid item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mThumbnailView;

        public ViewHolder(View view) {
            super(view);
            mThumbnailView = view.findViewById(R.id.iv_poster);
            }

            // todo: figure out how to go to DetailActivity onClick
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate imageView for thumbnail
        View movieView = inflater.inflate(R.layout.movie_grid_item, parent, false);

        // return new viewholder
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        ImageView thumbnailView = holder.mThumbnailView;
        String posterURL = NetworkUtils.buildPosterURLString(movie.getPoster_path());
        Picasso.with(thumbnailView.getContext()).load(posterURL).into(thumbnailView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
