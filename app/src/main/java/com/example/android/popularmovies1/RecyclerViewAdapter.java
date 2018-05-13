package com.example.android.popularmovies1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    final private OnClickListener mOnClickListener;
    private List<Movie> mMovies;

    // todo
    public interface OnClickListener {
        void onClick();
    }

    // set up viewholder for each grid item
    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.iv_poster);
            //todo view.setOnClickListener();
        }

        @Override
        public void onClick() {
            mOnClickListener.onClick();
        }
    }

    // constructor for adapter
    public RecyclerViewAdapter(List movies, OnClickListener listener) {
        mMovies = movies;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate imageView for thumbnail
        View movieView = inflater.inflate(R.layout.movie_grid_item, parent, false);

        // return new viewholder
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        ImageView thumbnailView = holder.thumbnail;
        // todo set image on thumbnailView
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
