package com.example.minh.servicesongdemo.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minh.servicesongdemo.R;
import com.example.minh.servicesongdemo.Song;
import com.example.minh.servicesongdemo.service.SongService;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private Context mContext;
    private List<Song> mListSong = new ArrayList<>();
    private ICallBack mListener;

    public ICallBack getmListener() {
        return mListener;
    }

    public void setmListener(ICallBack mListener) {
        this.mListener = mListener;
    }

    public SongAdapter(Context mContext, List<Song> mListSong) {
        this.mContext = mContext;
        this.mListSong = mListSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_song,null);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song mSong = mListSong.get(position);
        holder.mTextNameSong.setText(mSong.getmName());
    }

    @Override
    public int getItemCount() {
        return mListSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextNameSong;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextNameSong = itemView.findViewById(R.id.text_name_song);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.ClickItemMusic(mListSong.get(getPosition()).getmMp3());
                }
            });
        }
    }
}
