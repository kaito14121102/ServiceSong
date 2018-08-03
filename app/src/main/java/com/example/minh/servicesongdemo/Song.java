package com.example.minh.servicesongdemo;

public class Song {
    private String mName;
    private int mMp3;

    public Song(String mName, int mMp3) {
        this.mName = mName;
        this.mMp3 = mMp3;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmMp3() {
        return mMp3;
    }

    public void setmMp3(int mMp3) {
        this.mMp3 = mMp3;
    }
}
