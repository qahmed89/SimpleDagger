package com.example.simpledagger.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simpledagger.R;
import com.example.simpledagger.model.movies.MoviesItem;

import java.util.List;

public class profileAdapter extends  RecyclerView.Adapter<profileAdapter.ProfileHolder> {

List<MoviesItem> db ;
     Context context;
    OnMovieclickListener onMovieclickListener;



    public profileAdapter(List<MoviesItem> db, Context context, OnMovieclickListener onMovieclickListener) {
        this.db = db;
        this.context = context;
        this.onMovieclickListener = onMovieclickListener;
    }



    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new profileAdapter.ProfileHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview,parent,false),onMovieclickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.anime));

    //    holder.imageView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.anime));
      holder.title.setText(db.get(position).getTitle());
        Glide.with(context).load(db.get(position).getMediumCoverImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return db.size();
    }

    class ProfileHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;

        ImageView imageView;
        CardView cardView;
        OnMovieclickListener onMovieclickListener;

        public ProfileHolder(@NonNull View itemView,  OnMovieclickListener onMovieclickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            imageView = itemView.findViewById(R.id.cover_image);
            cardView=itemView.findViewById(R.id.cardview);
            this.onMovieclickListener=onMovieclickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onMovieclickListener.onMovieClick(getAdapterPosition());
        }
    }
    public interface  OnMovieclickListener{
        void onMovieClick(int Postion)  ;
    }
}
