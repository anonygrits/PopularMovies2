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
    private int mNumberItems;

    public RecyclerViewAdapter(int numberItems, OnClickListener listener) {
        mNumberItems = numberItems;
        mOnClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        TextView title;
        ImageView thumbnail;
        TextView year;
        TextView averageVote;
        TextView overview;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            thumbnail = view.findViewById(R.id.iv_thumbnail);
            year = view.findViewById(R.id.tv_year);
            averageVote = view.findViewById(R.id.tv_avgVote);
            overview = view.findViewById(R.id.tv_overview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onClick(view);
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
        return mNumberItems;
    }
}
