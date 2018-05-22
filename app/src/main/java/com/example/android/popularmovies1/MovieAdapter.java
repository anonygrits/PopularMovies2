package com.example.android.popularmovies1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.content.Context;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import com.example.android.popularmovies1.Utilities.NetworkUtils;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies;
    private final MovieOnClickHandler mClickHandler;

    public interface MovieOnClickHandler {
        void onClick(Movie movie);
    }
    // constructor for adapter
    public MovieAdapter(ArrayList movies, MovieOnClickHandler handler) {
        mMovies = movies;
        mClickHandler = handler;
    }

    // set up viewholder for each grid item
    public class MovieViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public ImageView mThumbnailView;

        public MovieViewHolder(View view) {
            super(view);
            mThumbnailView = view.findViewById(R.id.iv_poster);
            view.setOnClickListener(this);
            }
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie movie = mMovies.get(adapterPosition);
            mClickHandler.onClick(movie);
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate imageView for thumbnail
        View movieView = inflater.inflate(R.layout.movie_grid_item, parent, false);

        // return new viewholder
        MovieViewHolder viewHolder = new MovieViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        ImageView thumbnailView = holder.mThumbnailView;
        String posterURL = NetworkUtils.buildPosterURLString(movie.getPoster_path());
        Picasso.with(thumbnailView.getContext()).load(posterURL).into(thumbnailView);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) return 0;
        return mMovies.size();
    }
}
