package com.example.android.popularmovies1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private Context mContext;

    // constructor for adapter
    public RecyclerViewAdapter(Context context, List movies) {
        mContext = context;
        mMovies = movies;
    }

    // set up viewholder for each grid item
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mThumbnailView;
        private Context mContext;

        public ViewHolder(View view) {
            super(view);
            mThumbnailView = view.findViewById(R.id.iv_poster);

            mContext = view.getContext();
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            // todo: figure out how to go to DetailActivity onClick
        }
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

        ImageView thumbnailView = holder.mThumbnailView;
        Picasso.with(mContext).load(movie.getPoster_path()).into(thumbnailView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
