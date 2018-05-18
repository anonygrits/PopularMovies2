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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private Context mContext;  // todo figure out how to get context to picasso

    // constructor for adapter
    public MyAdapter(List movies) {
        mMovies = movies;
    }

    // set up viewholder for each grid item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mThumbnailView;

        public ViewHolder(View view) {
            super(view);
            mThumbnailView = view.findViewById(R.id.iv_poster);

            view.setOnClickListener(this);
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
        Picasso.with(mContext).load(movie.getPoster_path()).into(thumbnailView);
    }

    // todo figure out how to call notifyItemChanged() when switching lists

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
