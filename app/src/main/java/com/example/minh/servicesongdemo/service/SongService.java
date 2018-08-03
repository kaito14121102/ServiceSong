package com.example.minh.servicesongdemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.minh.servicesongdemo.MainActivity;
import com.example.minh.servicesongdemo.R;

public class SongService extends Service {

    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public int getTotalSong() {
        return mMediaPlayer.getDuration();
    }

    public int getCurrentSong() {
        return mMediaPlayer.getCurrentPosition();
    }

    public void PauseSong() {
        mMediaPlayer.pause();
    }

    public void StopSong() {
        mMediaPlayer.stop();
    }

    public void playSong(Context context, int mSongName){
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = MediaPlayer.create(context, mSongName);
                mMediaPlayer.start();
            }
        } else {
            mMediaPlayer = MediaPlayer.create(context, mSongName);
            mMediaPlayer.start();
        }
    }

    public class MyBinder extends Binder {
        public SongService getInstance() {
            return SongService.this;
        }
    }


}
