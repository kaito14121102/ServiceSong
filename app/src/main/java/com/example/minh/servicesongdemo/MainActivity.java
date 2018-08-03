package com.example.minh.servicesongdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.minh.servicesongdemo.adapter.ICallBack;
import com.example.minh.servicesongdemo.adapter.SongAdapter;
import com.example.minh.servicesongdemo.service.SongService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageButton mButtonPlay;
    private ImageButton mButtonBack;
    private ImageButton mButtonStop;
    private ImageButton mButtonNext;
    private RecyclerView mRecycleViewSong;
    private List<Song> mListSong;
    private SongAdapter mSongAdapter;
    private SeekBar mSeekBar;
    private SongService mSongService;
    private boolean mBound = false;
    public static final String SONG_NAME = "songname";
    private static int mPositionSong = -1;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SongService.MyBinder mBinder = (SongService.MyBinder) iBinder;
            mSongService = mBinder.getInstance();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startSongService();
        init();
        clickItemSong();
        
    }


    private void startSongService() {
        Intent mIntent = new Intent(MainActivity.this, SongService.class);
        bindService(mIntent, mConnection, MainActivity.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    private void init() {
        mButtonBack = findViewById(R.id.button_backward);
        mButtonNext = findViewById(R.id.button_forward);
        mButtonPlay = findViewById(R.id.button_play);
        mButtonStop = findViewById(R.id.button_stop);
        mSeekBar = findViewById(R.id.seek_bar);
        mRecycleViewSong = findViewById(R.id.recycle_view);
        mListSong = new ArrayList<>();
        mListSong.add(new Song("Song 1", R.raw.song1));
        mListSong.add(new Song("Song 2", R.raw.song3));
        mListSong.add(new Song("Song 3", R.raw.song2));
        mSongAdapter = new SongAdapter(MainActivity.this, mListSong);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycleViewSong.setLayoutManager(linearLayoutManager);
        mRecycleViewSong.setAdapter(mSongAdapter);
    }

    public void clickItemSong() {
        mSongAdapter.setmListener(new ICallBack() {
            @Override
            public void ClickItemMusic(int positon) {
                mPositionSong = positon;
                mSongService.playSong(getBaseContext(), mListSong.get(positon).getmMp3());
            }
        });
    }
}
