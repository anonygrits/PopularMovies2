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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Movie> mMovies;

    // set up viewholder for each grid item
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView thumbnail;
        private Context context;

        public ViewHolder(Context context, View view) {
            super(view);
            thumbnail = view.findViewById(R.id.iv_poster);

            this.context = context;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            // todo: figure out how to go to DetailActivity onClick
        }
    }

    // constructor for adapter
    public RecyclerViewAdapter(List movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate imageView for thumbnail
        View movieView = inflater.inflate(R.layout.movie_grid_item, parent, false);

        // return new viewholder
        ViewHolder viewHolder = new ViewHolder(context, movieView);
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
