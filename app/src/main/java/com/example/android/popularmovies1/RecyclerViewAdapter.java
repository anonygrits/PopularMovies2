package com.example.android.popularmovies1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    final private OnClickListener mOnClickListener;
    private int mGridSize;

    // todo
    public interface OnClickListener {
        void onClick();
    }

    // constructor for adapter
    public RecyclerViewAdapter(int gridSize, OnClickListener listener) {
        mGridSize = gridSize;
        mOnClickListener = listener;
    }

    // set up viewholder for each grid item
   class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        TextView title;
        ImageView poster;
        TextView year;
        TextView averageVote;
        TextView overview;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            poster = view.findViewById(R.id.iv_poster);
            year = view.findViewById(R.id.tv_year);
            averageVote = view.findViewById(R.id.tv_avgVote);
            overview = view.findViewById(R.id.tv_overview);
            //todo view.setOnClickListener();
        }

        @Override
        public void onClick() {
            mOnClickListener.onClick();
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // todo: figure this out
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // todo: figure this out
        return ;
    }

    @Override
    public int getItemCount() {
        // todo: figure this out
        return 1;
    }
}
